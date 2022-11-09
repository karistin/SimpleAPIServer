package com.lucas.osapi.service;


import com.lucas.osapi.entity.Cpuinfo;
import com.lucas.osapi.entity.ServerPoint;
import com.lucas.osapi.model.response.ListResult;
import com.lucas.osapi.model.response.SingleResult;
import org.influxdb.impl.InfluxDBResultMapper;
import org.springframework.stereotype.Service;

/**
 * packageName    : com.lucas.osapi.service
 * fileName       : CpuService
 * author         : lucas
 * date           : 2022-11-09
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-09        lucas       최초 생성
 */

@Service
public class CpuServiceImpl implements CpuService {
    @Override
    public SingleResult<Cpuinfo> getSysUsageByName(ServerPoint server) {
        return null;
    }

    @Override
    public ListResult<Cpuinfo> findAllSysUsage() {

        return null;
    }
}
