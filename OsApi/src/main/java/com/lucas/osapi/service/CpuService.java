package com.lucas.osapi.service;

import com.lucas.osapi.entity.Cpuinfo;
import com.lucas.osapi.entity.ServerPoint;
import com.lucas.osapi.model.response.ListResult;
import com.lucas.osapi.model.response.SingleResult;
import org.springframework.stereotype.Service;

import java.util.List;

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
 * 핵심적으로 가져오는것
 * CPU System Usage
 * CPU User Usage
 * CPU IDLE Usage
 * 특정 호스트 UID로 정보가져오기
 * 특정 호스트 UID로 CPU Usage
 * 전체 서버에서 CPU 사용량 , 이름 상위 5개 가져오기
 * 전체 서버에서 CPU 사용량 , 이름 상위 10개 가져오기
 *
 * */


public interface CpuService {




    /*
    * select * from cpu where uid='{uid}' order by desc LIMIT 1
    * uid 입력후
    *  */

    Cpuinfo getCpuAllByUid(long uid);


    List<Cpuinfo> getCpuUsageByuid(long uid);

    /*
     *  select TOP("idle",5), *  FROM cpu
     *  return Cpuinfo . hostname
     * */

    ListResult<Cpuinfo> findTopCpuUsage();

    ListResult<Cpuinfo> findAllCpuUsage();


}
