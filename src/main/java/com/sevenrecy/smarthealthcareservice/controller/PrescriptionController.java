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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
     * 创建处方（多个）
     * @param histories_id 病历id
     * @param drug_idList 药品id列表
     * @param drug_countList 药品数量列表
     * @param usagesList 药品用法列表
     * @return
     */
    @RequestMapping("/create_prescription_list")
    public Result createPrescriptionList(@RequestParam("histories_id") String histories_id,
                                         @RequestParam("drug_id_list") List<String> drug_idList,
                                         @RequestParam("drug_count_list") List<Short> drug_countList,
                                         @RequestParam("usages_list") List<String> usagesList) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        int size = drug_idList.size();
        List<Prescription> prescriptionList = new ArrayList<>();
        List<DrugBill> drugBillList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String  drug_id = drug_idList.get(i);
            int drug_count = drug_countList.get(i);
            String usages = usagesList.get(i);
            Drug drug = drugService.selectDrugById(drug_id);
            if (drug==null) {
                return Result.setResult(DRUG_NULL_ERROR);
            }
            Histories histories = historiesService.selectHistoriesById(histories_id);
            if (histories==null) {
                return Result.setResult(HISTORIES_NULL_ERROR);
            }
            Prescription prescription = prescriptionService.selectPrescription(histories_id, drug_id);
            if (prescription!=null) {
                prescriptionList.add(prescription);
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
                    drugBill.setPrice(drug.getPrice());
                    drugBill.setDrug_count(prescription.getDrug_count());
                    drugBill.setTotal(drugBill.getPrice()*drugBill.getDrug_count());
                    drugBill.setCount(histories.getCount());
                    drugBill.setIsPay("No");
                    drugBill.setCreate_time(prescription.getCreate_time());
                    drugBill.setDrug_bill_id("dbi"+prescription.getPrescription_id().substring(3));
                    int j = billService.createDrugBill(drugBill);
                    if (j>0) {
                        drugBill = billService.selectDrugBillByPreId(prescription.getPrescription_id());
                        if (drugBill!=null) {
                            drugBillList.add(drugBill);
                        } else {
                            drugBillList.add(null);
                        }
                    } else  {
                        drugBillList.add(null);
                    }
                } else {
                    drugBillList.add(drugBill);
                }
            } else {
                prescription = new Prescription();
                prescription.setHistories_id(histories.getHistories_id());
                prescription.setDrug_id(drug.getDrug_id());
                prescription.setDrug_name(drug.getName());
                prescription.setDrug_count(drug_count);
                prescription.setUsages(usages);
                SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
                String date = fmt1.format(new Date());
                prescription.setCreate_time(date);
                System.out.println(prescription.getCreate_time());
                prescription.setPrescription_id("pre"+histories_id.substring(3,10)+date.substring(14,16)+date.substring(17));
                int k = prescriptionService.insertPrescription(prescription);
                if (k>0) {
                    prescription = prescriptionService.selectPrescriptionById(prescription.getPrescription_id());
                    prescriptionList.add(prescription);
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
                            drugBill.setPrice(drug.getPrice());
                            drugBill.setDrug_count(prescription.getDrug_count());
                            drugBill.setTotal(drugBill.getPrice()*drugBill.getDrug_count());
                            drugBill.setCount(histories.getCount());
                            drugBill.setIsPay("No");
                            drugBill.setCreate_time(prescription.getCreate_time());
                            drugBill.setDrug_bill_id("dbi"+prescription.getPrescription_id().substring(3));
                            int j = billService.createDrugBill(drugBill);
                            if (j>0) {
                                drugBill = billService.selectDrugBillByPreId(prescription.getPrescription_id());
                                if (drugBill!=null) {
                                    drugBillList.add(drugBill);
                                } else {
                                    drugBillList.add(null);
                                }
                            } else {
                                drugBillList.add(drugBill);
                            }
                        } else {
                            drugBillList.add(drugBill);
                        }
                    } else {
                        prescriptionList.add(null);
                    }
                } else {
                    prescriptionList.add(null);
                }
            }
        }
        int i = historiesService.updatePrescriptionCount(size);
        if (i>0) {
            return Result.ok().data("prescriptionList", prescriptionList).data("drugBillList", drugBillList);
        }
        return Result.setResult(PCOUNT_UPDATE_ERROR).data("prescriptionList", prescriptionList).data("drugBillList", drugBillList);
    }

    /**
     * 创建一条新的处方 (弃用)
     * @param histories_id 病历id
     * @param drug_id 药品id
     * @param drug_count 药品数量
     * @param usages 药品用法
     * @return
     */
    @RequestMapping("/create_prescription")
    public Result createPrescription(@RequestParam("histories_id") String histories_id,
                                     @RequestParam("drug_id") String drug_id,
                                     @RequestParam("drug_count") int drug_count,
                                     @RequestParam("usages") String usages) {
        System.out.println(new Date() + "\t[SmartHealthCareService]\t" +  this.getClass().getName() + ":\t" + new Exception().getStackTrace()[0].getMethodName());
        return Result.ok().message("已弃用");
//        Drug drug = drugService.selectDrugById(drug_id);
//        if (drug==null) {
//            return Result.setResult(DRUG_NULL_ERROR);
//        }
//        Histories histories = historiesService.selectHistoriesById(histories_id);
//        if (histories==null) {
//            return Result.setResult(HISTORIES_NULL_ERROR);
//        }
//        Prescription prescription = prescriptionService.selectPrescription(histories_id, drug_id);
//        if (prescription!=null) {
//            DrugBill drugBill = billService.selectDrugBillByPreId(prescription.getPrescription_id());
//            if (drugBill==null) {
//                drugBill = new DrugBill();
//                drugBill.setDrug_id(prescription.getDrug_id());
//                drugBill.setPrescription_id(prescription.getPrescription_id());
//                drugBill.setHistories_id(prescription.getHistories_id());
//                drugBill.setUser_id(histories.getUser_id());
//                drugBill.setUser_name(histories.getUser_name());
//                drugBill.setDrug_id(drug.getDrug_id());
//                drugBill.setDrug_name(drug.getName());
//                drugBill.setPrice(drug.getPrice());
//                drugBill.setDrug_count(prescription.getDrug_count());
//                drugBill.setTotal(drugBill.getPrice()*drugBill.getDrug_count());
//                drugBill.setCount(histories.getCount());
//                drugBill.setIsPay("No");
//                drugBill.setCreate_time(prescription.getCreate_time());
//                drugBill.setDrug_bill_id("dbi"+prescription.getPrescription_id().substring(3));
//                int j = billService.createDrugBill(drugBill);
//                if (j>0) {
//                    drugBill = billService.selectDrugBillByPreId(prescription.getPrescription_id());
//                    if (drugBill!=null) {
//                        return Result.setResult(PRESCRIPTION_EXIST_ERROR).data("prescription",prescription).data("drugBill", drugBill);
//                    } else {
//                        return Result.setResult(DATABASE_ERROR).data("prescription",prescription);
//                    }
//                }
//                return Result.setResult(DRUG_BILL_CREATE_ERROR).data("prescription",prescription);
//            }
//            return Result.setResult(PANDB_EXIST_ERROR).data("prescription", prescription).data("drugBill", drugBill);
//        }
//        prescription = new Prescription();
//        prescription.setHistories_id(histories.getHistories_id());
//        prescription.setDrug_id(drug.getDrug_id());
//        prescription.setDrug_name(drug.getName());
//        prescription.setDrug_count(drug_count);
//        prescription.setUsages(usages);
//        SimpleDateFormat fmt1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        fmt1.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
//        String date = fmt1.format(new Date());
//        prescription.setCreate_time(date);
//        System.out.println(prescription.getCreate_time());
//        prescription.setPrescription_id("pre"+histories_id.substring(3,10)+date.substring(14,16)+date.substring(17));
//        int i = prescriptionService.insertPrescription(prescription);
//        if (i>0) {
//            prescription = prescriptionService.selectPrescriptionById(prescription.getPrescription_id());
//            if (prescription!=null) {
//                DrugBill drugBill = billService.selectDrugBillByPreId(prescription.getPrescription_id());
//                if (drugBill==null) {
//                    drugBill = new DrugBill();
//                    drugBill.setDrug_id(prescription.getDrug_id());
//                    drugBill.setPrescription_id(prescription.getPrescription_id());
//                    drugBill.setHistories_id(prescription.getHistories_id());
//                    drugBill.setUser_id(histories.getUser_id());
//                    drugBill.setUser_name(histories.getUser_name());
//                    drugBill.setDrug_id(drug.getDrug_id());
//                    drugBill.setDrug_name(drug.getName());
//                    drugBill.setPrice(drug.getPrice());
//                    drugBill.setDrug_count(prescription.getDrug_count());
//                    drugBill.setTotal(drugBill.getPrice()*drugBill.getDrug_count());
//                    drugBill.setCount(histories.getCount());
//                    drugBill.setIsPay("No");
//                    drugBill.setCreate_time(prescription.getCreate_time());
//                    drugBill.setDrug_bill_id("dbi"+prescription.getPrescription_id().substring(3));
//                    int j = billService.createDrugBill(drugBill);
//                    if (j>0) {
//                        drugBill = billService.selectDrugBillByPreId(prescription.getPrescription_id());
//                        if (drugBill!=null) {
//                            return Result.ok().data("prescription",prescription).data("drugBill", drugBill);
//                        } else {
//                            return Result.setResult(DATABASE_ERROR).data("prescription",prescription);
//                        }
//                    }
//                    return Result.setResult(DRUG_BILL_CREATE_ERROR).data("prescription",prescription);
//                }
//                return Result.ok().data("prescription",prescription).data("drugBill", drugBill);
//            }
//            return Result.setResult(DATABASE_ERROR);
//        }
//        return Result.setResult(DATABASE_ERROR);
    }

    /**
     * 获取处方信息
     * @param prescription_id 处方id
     * @return
     */
    @RequestMapping("/get_prescription")
    public Result getPrescription(@RequestParam("prescription_id") String prescription_id) {
        System.out.println(new Date() + "[SmartHealthCareService]" +  this.getClass().getName() + ": " + new Exception().getStackTrace()[0].getMethodName());
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
        System.out.println(new Date() + "[SmartHealthCareService]" +  this.getClass().getName() + ": " + new Exception().getStackTrace()[0].getMethodName());
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
