package com.lucas.dev.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.assertj.core.api.Assertions.assertThat;
import com.lucas.osapi.entity.DiskUsage;
import com.lucas.osapi.entity.ServerInfo;
import com.lucas.osapi.repo.influxDB.DiskRepo;
import com.lucas.osapi.repo.influxDB.DiskRepoImpl;
import com.lucas.osapi.repo.influxDB.ServerRepo;
import com.lucas.osapi.repo.influxDB.ServerRepoImpl;
import com.lucas.osapi.service.DiskUsageServiceimpl;
import com.lucas.osapi.service.ServerService;
import com.lucas.osapi.service.ServerServiceImpl;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * packageName    : com.lucas.dev.service fileName       : ServerServiceTest author         : lucas
 * date           : 2022-12-12 description    :
 * =========================================================== DATE              AUTHOR NOTE
 * ----------------------------------------------------------- 2022-12-12        lucas       최초 생성
 */
@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class ServerServiceTest {

    private final ServerRepo serverRepo = mock(ServerRepoImpl.class);
// findList
//  findById
//    count

    private final ServerService serverService = new ServerServiceImpl(serverRepo);

    private final List<ServerInfo> serverList = new ArrayList<>();


    @Before
    public void initData() {
        serverList.add(new ServerInfo().setServerInfo(Instant.now(), "serverA", "serverA",
            "linux", "1.1.0", "7.89Gib", 4, "1.1.1.1",
            "1.2.0"));
        serverList.add(new ServerInfo().setServerInfo(Instant.now(), "serverB", "serverB",
            "linux", "1.1.0", "7.89Gib", 4, "1.1.1.1",
            "1.2.0"));
        serverList.add(new ServerInfo().setServerInfo(Instant.now(), "serverC", "serverC",
            "window", "1.1.0", "7.89Gib", 4, "1.1.1.1",
            "1.2.0"));
        serverList.add(new ServerInfo().setServerInfo(Instant.now(), "serverD", "serverD",
            "unix", "1.1.0", "7.89Gib", 4, "1.1.1.1",
            "1.2.0"));
        serverList.add(new ServerInfo().setServerInfo(Instant.now(), "serverE", "servere",
            "linux", "1.1.0", "7.89Gib", 4, "128.1.21.12",
            "1.2.0"));
        serverList.add(new ServerInfo().setServerInfo(Instant.now(), "serverF", "serverF",
            "window", "1.1.0", "7.89Gib", 4, "192.1.1.1",
            "1.2.0"));
        serverList.add(new ServerInfo().setServerInfo(Instant.now(), "serverG", "serverG",
            "linux", "1.1.0", "7.89Gib", 10, "203.1.1.1",
            "1.2.0"));
        given(serverRepo.findList())
            .willReturn(serverList);
    }


    @Test
    @DisplayName("ServerType API")
    public void getServerTypeTest() {
        Map<String, Integer> data = serverService.getServerType();
        List<String> serverType = new ArrayList<>(
            Arrays.asList("linux", "window", "unix", "other")
//            4, 2, 1, 0
        );

        data.forEach((key, value) -> assertTrue(serverType.contains(key)));
        Assertions.assertThat(data.get("linux")).isEqualTo(4);
        Assertions.assertThat(data.get("window")).isEqualTo(2);
        Assertions.assertThat(data.get("unix")).isEqualTo(1);
    }

    @Test
    @DisplayName("ServerCore API")
    public void getServerCoreTest() {
        assertTrue(serverService.getServerCore().isPresent());
        assertThat(serverService.getServerCore().get()).isEqualTo(34);
    }

    @Test
    @DisplayName("ServerCout")
    public void getServerCountTest() {
        assertThat(serverService.count().getValue()).isEqualTo(serverList.size());
    }
}
