package com.lucas.osapi.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;

import com.lucas.osapi.entity.DiskInfo;
import com.lucas.osapi.entity.DiskUsage;
import com.lucas.osapi.repo.influxDB.DiskRepo;
import com.lucas.osapi.repo.influxDB.DiskRepoImpl;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

/**
 * packageName    : com.lucas.osapi.service fileName       : DiskUsageServiceTest author         :
 * lucas date           : 2022-12-08 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2022-12-08        lucas       최초
 * 생성
 */
@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class DiskUsageServiceTest {


    @Mock
    private DiskRepo diskRepo;

    @InjectMocks
    private DiskUsageServiceimpl diskUsageServiceimpl;


//    @Before
//    public void mocking() {
//        MockitoAnnotations.initMocks(this);
//    }


    @Test
    public void TopTest() throws Exception {

        diskRepo = mock(DiskRepoImpl.class);


        List<DiskUsage> diskUsage = new ArrayList<>();
        diskUsage.add(new DiskUsage(10, 20, 30, "A"));
        diskUsage.add(new DiskUsage(20, 20, 31, "B"));
        diskUsage.add(new DiskUsage(30, 200, 32, "C"));
        diskUsage.add(new DiskUsage(40, 2, 33, "D"));
        diskUsage.add(new DiskUsage(50, 40, 34, "E"));
        diskUsage.add(new DiskUsage(60, 600, 35, "F"));

        List<DiskUsage> diskUsageSorted = new ArrayList<>();
        lenient().when(diskRepo.findListUsage())
            .thenReturn(diskUsage);
//        Assert.assertArrayEquals(diskUsageServiceimpl.Top("diskInodeUsed"),
//            diskUsage.stream().sorted(Comparator.comparing(DiskUsage::getDiskInodeUsed)).forEach(diskUsageSorted::add));
        List<DiskUsage> diskUsages = diskUsageServiceimpl.Top("diskInodeUsed");
        for (DiskUsage usage : diskUsages) {
            System.out.println(usage.toString());
        }
    }


}
