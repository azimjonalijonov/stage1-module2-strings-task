package com.epam.mjc;

import java.util.ArrayList;
import java.util.List;

public class MethodParser {

    /**
     * Parses string that represents a method signature and stores all it's members into a {@link MethodSignature} object.
     * signatureString is a java-like method signature with following parts:
     *      1. access modifier - optional, followed by space: ' '
     *      2. return type - followed by space: ' '
     *      3. method name
     *      4. arguments - surrounded with braces: '()' and separated by commas: ','
     * Each argument consists of argument type and argument name, separated by space: ' '.
     * Examples:
     *      accessModifier returnType methodName(argumentType1 argumentName1, argumentType2 argumentName2)
     *      private void log(String value)
     *      Vector3 distort(int x, int y, int z, float magnitude)
     *      public DateTime getCurrentDateTime()
     *
     * @param signatureString source string to parse
     * @return {@link MethodSignature} object filled with parsed values from source string
     */
    public MethodSignature parseFunction(String signatureString) {
        String[] split = signatureString.split("\\(");
        List<MethodSignature.Argument> arguments = new ArrayList<>();
        String accessM = "";
        String returnType = "";
        String methodName = "";
        for (int i = 0; i < split.length; i++)  {
            if (i == 0) {
                String[] modToName = split[i].split(" ");
                if (modToName.length == 3) {
                    accessM = modToName[0];
                    returnType = modToName[1];
                    methodName = modToName[2];
                } else if (modToName.length == 2) {
                    accessM = null;
                    returnType = modToName[0];
                    methodName = modToName[1];
                }

            }else {
                String[] methodArg = split[i].split(", ");
                for (String arg : methodArg) {
                    String[] oneArg = arg.split(" ");
                    if (oneArg.length == 2){
                        String argName = oneArg[1];
                        if (argName.endsWith(")")){
                            argName = argName.substring(0, argName.length()-1);
                        }
                        MethodSignature.Argument argument = new MethodSignature.Argument(oneArg[0], argName);
                        arguments.add(argument);
                    }
                }
            }
        }
        MethodSignature methodSignature = new MethodSignature(methodName, arguments);
        methodSignature.setAccessModifier(accessM);
        methodSignature.setReturnType(returnType);
        return methodSignature;
    }
}
