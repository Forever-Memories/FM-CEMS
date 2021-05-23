package project.zzq.competition_epidemic_management_system.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.zzq.competition_epidemic_management_system.constant.CompetitionEpidemicManagementSystemConstant;
import project.zzq.competition_epidemic_management_system.web.data.NoticeVO;
import project.zzq.competition_epidemic_management_system.web.data.WarnVO;
import project.zzq.competition_epidemic_management_system.web.logic.NoticeLogic;

import java.util.List;

@RestController
@RequestMapping(CompetitionEpidemicManagementSystemConstant.API)
public class NoticeController {
    @Autowired
    private NoticeLogic noticeLogic;

    @GetMapping("/notice/all-info")
    public List<NoticeVO> getAllNotice() {
        return noticeLogic.getAllNotice();
    }

}
