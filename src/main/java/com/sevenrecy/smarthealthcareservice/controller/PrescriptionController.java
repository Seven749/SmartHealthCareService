package com.sevenrecy.smarthealthcareservice.controller;

import com.sevenrecy.smarthealthcareservice.entity.Histories;
import com.sevenrecy.smarthealthcareservice.entity.Prescription;
import com.sevenrecy.smarthealthcareservice.json.Result;
import com.sevenrecy.smarthealthcareservice.service.HistoriesService;
import com.sevenrecy.smarthealthcareservice.service.PrescriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum.HISTORIES_NULL_ERROR;
import static com.sevenrecy.smarthealthcareservice.json.ResultCodeEnum.PRESCRIPTION_NULL_ERROR;

@RestController
@RequestMapping("/api")
public class PrescriptionController {
    @Autowired
    PrescriptionService prescriptionService;
    @Autowired
    HistoriesService historiesService;

    @RequestMapping("/create_presciption")
    public Result createPrescription() {

        return null;
    }

    /**
     * 获取处方信息
     * @param prescription_id 处方id
     * @return
     */
    @RequestMapping("/get_prescription")
    public Result getPrescription(@RequestParam("prescription_id") String prescription_id) {
        Prescription prescription = prescriptionService.selectPrescription(prescription_id);
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
