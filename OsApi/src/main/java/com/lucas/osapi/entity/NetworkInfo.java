package com.lucas.osapi.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.influxdb.annotation.Measurement;

import java.util.concurrent.TimeUnit;

/**
 * packageName    : com.lucas.osapi.entity
 * fileName       : NetworkInfo
 * author         : lucas
 * date           : 2022-11-11
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-11        lucas       최초 생성
 */

@Getter
@Setter
@Builder
@Measurement(name = "NetworkInfo", database = "TimeSeries", retentionPolicy = "autogen", timeUnit = TimeUnit.MILLISECONDS)
public class NetworkInfo {
}
