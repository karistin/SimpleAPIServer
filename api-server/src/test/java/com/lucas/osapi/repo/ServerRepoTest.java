package com.lucas.osapi.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import com.lucas.osapi.config.InfluxDBConfiguration;
import com.lucas.osapi.entity.DiskInfo;
import com.lucas.osapi.entity.ServerInfo;
import com.lucas.osapi.repo.influxDB.MemRepoImpl;
import com.lucas.osapi.repo.influxDB.ServerRepo;
import com.lucas.osapi.repo.influxDB.ServerRepoImpl;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * packageName    : com.lucas.osapi.repo fileName       : ServerRepoTest author         : lucas date
 *           : 2022-12-12 description    :
 * =========================================================== DATE              AUTHOR
 * NOTE ----------------------------------------------------------- 2022-12-12        lucas       최초
 * 생성
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {ServerRepoImpl.class, InfluxDBConfiguration.class})
@Slf4j
public class ServerRepoTest {

    @Autowired
    private ServerRepo serverRepo;

    private final String key = "serverA";

    @Test
    @DisplayName("findCount")
    public void findCount() {
        Map<String, Integer> data = serverRepo.findCount();
        Assertions.assertThat(data.size()).isEqualTo(1);
    }

    @Test
    @DisplayName("findById")
    public void findById(){
        ServerInfo serverInfo = serverRepo.findById(key);
        assertNotNull(serverInfo);
        assertThat(serverInfo).hasNoNullFieldsOrProperties();
    }

    @Test
    @DisplayName("findList")
    public void findList(){
        List<ServerInfo> serverInfoList  = serverRepo.findList();
        assertNotNull(serverInfoList);
        serverInfoList.forEach(ServerInfo -> assertThat(ServerInfo).hasNoNullFieldsOrProperties());
    }

}
