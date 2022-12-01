package com.lucas.osapi.repo;

import java.util.List;

/**
 * packageName    : com.lucas.osapi.repo
 * fileName       : TimeRepository
 * author         : lucas
 * date           : 2022-11-21
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-21        lucas       최초 생성
 * DB의 여부에 따라서 바뀌는 repo
 * 저장시에는 info 형식을 따라야 한다. : 고정된 DB의 col 유지하기
 * T1 : all Data
 */
public interface TimeRepository<T> {
    List<T> findList();

    T findById(String key);

    List<T> findByIdRange(String key, String time);
}
