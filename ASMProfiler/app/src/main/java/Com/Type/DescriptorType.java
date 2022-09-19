package Com.Type;

import java.util.*;

public class DescriptorType {

    /*
    FieldType
      BaseType
      ObjectType
      ArrayType

    BaseType:
        B
        C
        D
        F
        I
        J
        S
        Z

    ObjectType:
        L ClassName ;
        ex) Ljava/lang/String => String , Ljava/util/Locale=> Locale(Local type)

    ArrayType:
        [ ComponentType
        ex) [Ljava/lang/Object; => Object[]

    ( ParameterDescriptor* ) ReturnDescriptor
    */
    private String[] inputArray;
    private String[] outputArray;
    public List<String> input = new ArrayList<String>();
    public List<String> output = new ArrayList<String>();
    private void parsingInOut(String descriptor){
        String input = "";
        String output = "";
        boolean flags = true;
        char[] array = descriptor.toCharArray();
        for (char arr: array){
            if (arr =='(' || arr == ')')
            {
                if (arr == ')')
                    flags = false;
                continue;
            }
            if(flags)
            {
                input +=String.valueOf(arr);
                continue;
            }
            output += String.valueOf(arr);
        }

        this.inputArray = input.split(";");
        this.outputArray = output.split(";");
        parsingList(this.input,this.inputArray);

        parsingList(this.output,this.outputArray);
    }

    private static final HashMap<String, String> baseType = new HashMap<>(){
        {
            put("Z", "boolean");
            put("C", "Char");
            put("B", "byte");
            put("S", "short");
            put("I", "int");
            put("F", "float");
            put("J", "long");
            put("D", "double");
            put("V", "void");
        }
    };



    private void parsingList(List<String> desc,String[] array){
        for (String arr: array)
        {
            String typeWord = "";
            if (!arr.equals(""))
            {
                String type = String.valueOf(arr.charAt(0));
                //System.out.println(type);
                if (baseType.keySet().contains(type))
                {
                    desc.add(baseType.get(type));
                }
                else if (type.equals("L"))
                {
                    List<String> arrr = List.of(arr.split("/"));
                    //System.out.println(Arrays.deepToString(arrr));
                    desc.add(arrr.get(arrr.size()-1));
                }
                else if (type.equals("[")) {
                    for(char ar : arr.toCharArray()){
                        if (ar == '[')
                            typeWord += "[]";
                        else if (baseType.keySet().contains(String.valueOf(ar)))
                        {
                            typeWord += baseType.get(type);
                        }
                    }
                    List<String> arrr = List.of(arr.split("/"));
                    desc.add(typeWord + arrr.get(arrr.size()-1));
                }
            }


        }
    }
    public List<List<String>>  getDescriptorType(String descriptor) {
        List<List<String>> result = new ArrayList<>();
        this.parsingInOut(descriptor);
        result.add(this.input);
        result.add(this.output);
        return result;
    }


}
