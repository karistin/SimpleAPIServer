/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package Java_agent;

import Entity.DataSet;
import Entity.DescriptorType;
import Entity.Flag;
import Package.GetClass;
import repo.DataSetRepo;
import repo.DataSetRepoMemory;

import java.io.IOException;

import java.lang.String;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {

    static DataSetRepo taskRepository = new DataSetRepoMemory();

    public static void main(String[] args) throws IOException {

        Flag flag = new Flag();
        String ClassName = "java.lang.String";
        //DescriptorType descriptorType = new DescriptorType();
        //System.out.println(descriptorType.getDescriptorType("(Ljava/lang/CharSequence;B;[[Ljava/lang/CharSequence;)Ljava/lang/String;"));

        DataSet data = GetClass.getClass(ClassName);
        taskRepository.save(data);
        data.printDataset();


    }
}

