package com.lucas.osapi.service;

import com.lucas.osapi.entity.DiskInfo;
import com.lucas.osapi.entity.DiskUsage;
import com.lucas.osapi.repo.influxDB.DiskRepo;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import java.util.Optional;

/**
 * packageName    : com.lucas.osapi.service
 * fileName       : DiskUsageServiceimpl
 * author         : lucas
 * date           : 2022-11-17
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2022-11-17        lucas       최초 생성
 */
@Slf4j
@Service
public class DiskUsageServiceimpl implements DiskUsageService {

    private final DiskRepo diskRepo;

    public DiskUsageServiceimpl(DiskRepo diskRepo) {
        this.diskRepo = diskRepo;
    }

    @Override
    public List<DiskInfo> List() {
        return diskRepo.findList();
    }

    @Override
    public List<DiskUsage> ListUsage() {
        return diskRepo.findListUsage();
    }

    @Override
    public DiskInfo Id(String id) {
        return diskRepo.findById(id);
    }

    @Override
    public List<DiskInfo> IdRange(String key, Long time) {
        return diskRepo.findByIdRange(key, time);
    }

    @Override
    public List<DiskUsage> IdRangeUsage(String key, Long time) {
        return diskRepo.findByIdRangeUsage(key, time);
    }

    @Override
    public Optional<Double> Average() {
        List<DiskUsage> diskUsage = diskRepo.findListUsage();
        return Optional.of(
            diskUsage.stream().mapToDouble(DiskUsage::getDiskUsage).average().orElseThrow());
    }

    @Override
    public Optional<Double> Max() {
        List<DiskUsage> diskUsage = diskRepo.findListUsage();
        return Optional.of(
            diskUsage.stream().mapToDouble(DiskUsage::getDiskUsage).max().orElseThrow());
    }

    @Override
    public Optional<Double> Min() {
        List<DiskUsage> diskUsage = diskRepo.findListUsage();
        return Optional.of(
            diskUsage.stream().mapToDouble(DiskUsage::getDiskUsage).min().orElseThrow());
    }

    @Override
    public List<DiskUsage> Top(String colType) {
        List<DiskUsage> diskUsage = diskRepo.findListUsage();
        List<DiskUsage> topValue = new ArrayList<>();

        switch (colType) {
            case "diskUsage":
                diskUsage.stream().sorted(Comparator.comparing(DiskUsage::getDiskUsage)
                        .reversed()).limit(5)
                    .forEach(topValue::add);
                break;
            case "diskIOPS":
                diskUsage.stream().sorted(Comparator.comparing(DiskUsage::getDiskIOPS)
                        .reversed()).limit(5)
                    .forEach(topValue::add);
                break;
            case "diskInodeUsed":
                diskUsage.stream().sorted(Comparator.comparing(DiskUsage::getDiskInodeUsed)
                        .reversed()).limit(5)
                    .forEach(topValue::add);
                break;
            default:
                throw new RuntimeException();
        }

        return topValue;
    }
}
