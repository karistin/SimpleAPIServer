package com.lucas.osapi.dev.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import com.lucas.osapi.entity.DiskUsage;
import com.lucas.osapi.repo.influxDB.DiskRepo;
import com.lucas.osapi.repo.influxDB.DiskRepoImpl;
import com.lucas.osapi.service.DiskUsageServiceimpl;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;


/**
 * packageName    : com.lucas.osapi.service fileName       : DiskUsageServiceTest author         :
 * lucas date           : 2022-12-08 description    :
 * =========================================================== DATE              AUTHOR NOTE
 * ----------------------------------------------------------- 2022-12-08        lucas       최초 생성
 */
@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class DiskUsageServiceTest {


    private final DiskRepo diskRepo = mock(DiskRepoImpl.class);


    private final DiskUsageServiceimpl diskUsageServiceimpl = new DiskUsageServiceimpl(diskRepo);

    private final List<DiskUsage> diskUsageList = new ArrayList<>();

//    TODO : IOPS Col change Iops
    @Before
    public void mocking() {
        diskUsageList.add(new DiskUsage(Instant.now(),10, 20, 30, "A"));
        diskUsageList.add(new DiskUsage(Instant.now(),20, 20, 31, "B"));
        diskUsageList.add(new DiskUsage(Instant.now(),30, 200, 32, "C"));
        diskUsageList.add(new DiskUsage(Instant.now(),40, 2, 33, "D"));
        diskUsageList.add(new DiskUsage(Instant.now(),50, 40, 34, "E"));
        diskUsageList.add(new DiskUsage(Instant.now(),60, 600, 35, "F"));
        given(diskRepo.findListUsage())
            .willReturn(diskUsageList);
    }


    @Test
    @DisplayName("diskInodeUsedTest")
    public void inodeTopTest() {
        List<DiskUsage> diskUsageSorted = diskUsageServiceimpl.Top("diskInodeUsed");
        List<String> sortedUid = new ArrayList<>();
        diskUsageSorted.forEach(o -> sortedUid.add(o.getUid()));
        assertEquals(sortedUid, Arrays.asList("F", "E", "D", "C", "B", "A"));
    }

    @Test
    @DisplayName("diskIopsTest")
    public void iopsTopTest() {
        List<DiskUsage> diskUsageSorted = diskUsageServiceimpl.Top("diskIOPS");
        List<String> sortedUid = new ArrayList<>();
        diskUsageSorted.forEach(o -> sortedUid.add(o.getUid()));
        assertEquals(Arrays.asList("F", "C", "E", "A", "B", "D") , sortedUid);
    }

    @Test
    @DisplayName("diskIopsTest")
    public void usageTopTest() {
        List<DiskUsage> diskUsageSorted = diskUsageServiceimpl.Top("diskUsage");
        List<String> sortedUid = new ArrayList<>();
        diskUsageSorted.forEach(o -> sortedUid.add(o.getUid()));
        assertEquals(Arrays.asList("F", "E", "D", "C", "B", "A") , sortedUid);
    }


}
