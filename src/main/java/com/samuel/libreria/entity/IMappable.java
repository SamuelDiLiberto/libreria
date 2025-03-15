package com.samuel.libreria.entity;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.Map;

public interface IMappable {
    default void fromMap(Map<String,String> map){
        for(Method m : this.getClass().getMethods()){
            if(m.getName().startsWith("set") && m.getParameterCount() == 1){
                String nomeProprieta = m.getName().substring(3);
                nomeProprieta = Character.toLowerCase(nomeProprieta.charAt(0)) + nomeProprieta.substring(1);
                if(map.containsKey(nomeProprieta)){
                    String valoreAssociato = map.get(nomeProprieta);
                    if(valoreAssociato == null){
                        continue;
                    }
                    String tipoParametro = m.getParameters()[0].getType().getSimpleName().toLowerCase();
                    try {
                        switch(tipoParametro){
                            case "string":
                                m.invoke(this,valoreAssociato);
                            break;
                            case "int":
                                m.invoke(this, Integer.parseInt(valoreAssociato));
                            break;
                            case "double":
                                m.invoke(this, Double.parseDouble(valoreAssociato));
                            break;
                            case "localdate":
                                m.invoke(this, LocalDate.parse(valoreAssociato));
                            break;
                            case "boolean":
                                m.invoke(this, (valoreAssociato.equals("1")?true:false));
                            break;
                            case "long":
                                m.invoke(this, Long.parseLong(valoreAssociato));
                            break;
                        }
                    } catch (Exception ex) {
                       ex.printStackTrace();
                    }

                }
            }
        }

    }
}
