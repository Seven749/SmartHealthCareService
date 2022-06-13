package com.sevenrecy.smarthealthcareservice.controller;

import com.sevenrecy.smarthealthcareservice.entity.Drug;
import com.sevenrecy.smarthealthcareservice.entity.DrugBill;
import com.sevenrecy.smarthealthcareservice.entity.Histories;
import com.sevenrecy.smarthealthcareservice.entity.Prescription;
import com.sevenrecy.smarthealthcareservice.json.Result;
import com.sevenrecy.smarthealthcareservice.service.BillService;
import com.sevenrecy.smarthealthcareservice.service.DrugService;
import com.sevenrecy.smarthealthcareservice.service.HistoriesService;
import com.sevenrecy.smarthealthcareservice.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:8080",maxAge = 3600)
public class PrescriptionController {
    @Autowired
    PrescriptionService prescriptionService;
    @Autowired
    HistoriesService historiesService;
    @Autowired
    DrugService drugService;
    @Autowired
    BillService billService;

    /**
     * 创建一条新的处方
     * @param histories_id 病历id
     * @param drug_id 药品id
     * @param drug_count 药品数量
     * @param usage 药品用法
     * @return
     */
    @RequestMapping("/create_presciption")
    public Result createPrescription(@RequestParam("histories_id") String histories_id,
                                     @RequestParam("drug_id") String drug_id,
                                     @RequestParam("drug_count") int drug_count,
                                     @RequestParam("usage") String usage) {
        Prescription prescription = prescriptionService.selectPrescription(histories_id, drug_id);
        if (prescription!=null) {
            return Result.setResult(PRESCRIPTION_EXIT_ERROR).data("prescription", prescription);
        }
        Drug drug = drugService.selectDrugById(drug_id);
        if (drug==null) {
            return Result.setResult(DRUG_NULL_ERROR);
        }
        Histories histories = historiesService.selectHistoriesById(histories_id);
        if (histories==null) {
            return Result.setResult(HISTORIES_NULL_ERROR);
        }
        prescription = new Prescription();
        prescription.setHistories_id(histories.getHistories_id());
        prescription.setDrug_id(drug.getDrug_id());
        prescription.setDrug_name(drug.getName());
        prescription.setDrug_count(drug_count);
        prescription.setUsage(usage);
        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String date = fmt1.format(new Date());
        prescription.setCreate_time(date);
        prescription.setPrescription_id("pre"+histories_id.substring(3,10)+date.substring(15,17)+date.substring(18,20));
        int i = prescriptionService.insertPrescription(prescription);
        if (i>0) {
            prescription = prescriptionService.selectPrescriptionById(prescription.getPrescription_id());
            if (prescription!=null) {
                DrugBill drugBill = billService.selectDrugBillByPreId(prescription.getPrescription_id());
                if (drugBill==null) {
                    drugBill = new DrugBill();
                    drugBill.setDrug_id(prescription.getDrug_id());
                    drugBill.setPrescription_id(prescription.getPrescription_id());
                    drugBill.setHistories_id(prescription.getHistories_id());
                    drugBill.setUser_id(histories.getUser_id());
                    drugBill.setUser_name(histories.getUser_name());
                    drugBill.setDrug_id(drug.getDrug_id());
                    drugBill.setDrug_name(drug.getName());
                    drugBill.setDrug_count(prescription.getDrug_count());
                    drugBill.setIsPay("No");
                    int j = billService.createDrugBill(drugBill);
                    if (j>0) {
                        drugBill = billService.selectDrugBillByPreId(prescription.getPrescription_id());
                        if (drugBill!=null) {
                            return Result.ok().data("prescription",prescription).data("drugBill", drugBill);
                        } else {
                            return Result.setResult(DATABASE_ERROR).data("prescription",prescription);
                        }
                    }
                    return Result.setResult(DRUG_BILL_CREATE_ERROR).data("prescription",prescription);
                }
                return Result.ok().data("prescription",prescription).data("drugBill", drugBill);
            }
            return Result.setResult(DATABASE_ERROR);
        }
        return Result.setResult(DATABASE_ERROR);
    }

    /**
     * 获取处方信息
     * @param prescription_id 处方id
     * @return
     */
    @RequestMapping("/get_prescription")
    public Result getPrescription(@RequestParam("prescription_id") String prescription_id) {
        Prescription prescription = prescriptionService.selectPrescriptionById(prescription_id);
        if (prescription!=null) {
            return Result.ok().data("prescription", prescription);
        }
        return Result.setResult(PRESCRIPTION_NULL_ERROR);
    }

    /**
     * 获取对应病历的处方列表
     * @param histories_id 病历id
     * @return
     */
    @RequestMapping("/get_prescription_list")
    public Result getPrescriptionList(@RequestParam("histories_id") String histories_id) {
        Histories histories = historiesService.selectHistoriesById(histories_id);
        if (histories!=null) {
            List<Prescription> prescriptionList = prescriptionService.selectPrescriptionList(histories_id);
            if (prescriptionList!=null&&prescriptionList.size()>0) {
                return Result.ok().data("prescriptionList", prescriptionList);
            }
            return Result.setResult(PRESCRIPTION_NULL_ERROR);
        }
        return Result.setResult(HISTORIES_NULL_ERROR);
    }
}
