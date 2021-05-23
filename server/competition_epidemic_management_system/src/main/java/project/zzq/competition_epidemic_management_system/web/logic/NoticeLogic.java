package project.zzq.competition_epidemic_management_system.web.logic;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.zzq.competition_epidemic_management_system.data.NoticeDO;
import project.zzq.competition_epidemic_management_system.storage.NoticeStorage;
import project.zzq.competition_epidemic_management_system.web.data.NoticeVO;

import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoticeLogic {
    @Autowired
    private NoticeStorage noticeStorage;

    public List<NoticeVO> getAllNotice() {
        return noticeStorage.getAllNotice().stream().sorted(Comparator.comparingLong(NoticeDO::getTime).reversed()).map(this::do2VO).collect(Collectors.toList());
    }

    private NoticeVO do2VO(NoticeDO noticeDO) {
        NoticeVO noticeVO = new NoticeVO();
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        noticeVO.setId(noticeDO.getId());
        noticeVO.setNotice(noticeDO.getNotice());
        noticeVO.setTime(format.format(noticeDO.getTime()));

        return noticeVO;
    }
}
