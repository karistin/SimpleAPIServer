package com.lucas.osapi.service;

import com.lucas.osapi.entity.Cpuinfo;
import com.lucas.osapi.model.response.ListResult;

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
* TODO: 특정 기간의 uid의 CPU사용량 가져오기
*
* */
    /*
    * Time now()
    * uid가 가지고 있는 Cpu data 출력
    *  */
    Cpuinfo getCpuAllByUid(long uid);

    /*
    * Time now()
    * 특정 uid가 사용하는 CPU 사용량 구하기
    * */
    List<Cpuinfo> getCpuUsageByuid(long uid);

    /*
     *  Time now()
     *  CPU 사용량 상위 5개 출력
     * */

    List<Cpuinfo> findTopCpuUsage();

    /*
    * Time now()
    * 모든 CPU의  사용량 구하기
    * */
    ListResult<Cpuinfo> findAllCpuUsage();


}
