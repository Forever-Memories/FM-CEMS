package project.zzq.competition_epidemic_management_system.web.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.zzq.competition_epidemic_management_system.data.BusDO;
import project.zzq.competition_epidemic_management_system.service.BusService;
import project.zzq.competition_epidemic_management_system.web.data.BusVO;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusLogic {
    @Autowired
    private BusService busService;

    public void create(BusDO busDO) {
        busService.create(busDO);
    }

    public List<BusVO> getAllBus() {
        return busService.getAllBus().stream().map(this::do2VO).collect(Collectors.toList());
    }

    private BusVO do2VO(BusDO busDO) {
        BusVO busVO = new BusVO();
        SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm");

        busVO.setId(busDO.getId());
        busVO.setTime(format.format(busDO.getTime()));
        busVO.setArrangement(busDO.getArrangement());
        busVO.setPlaceName(busDO.getPlaceName());
        busVO.setComment(busDO.getComment());

        return busVO;
    }
}
