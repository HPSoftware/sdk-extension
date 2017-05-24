/*
 * Copyright 2017 Hewlett-Packard Enterprise Development Company, L.P.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hpe.adm.nga.sdk.extension;


import com.hpe.adm.nga.sdk.model.EntityModel;
import com.hpe.adm.nga.sdk.model.FieldModel;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Collection;
import java.util.Set;

public class Util {

    /**
     * Simple debug method used to print a list of EntityModel objects to the console
     * For each entity, it will try to print the type + the fields [name] or [text]
     * If the fields that it tries to print do not exists, it will only print the type of the entity
     * @param entities
     */
    public static void printEntities(Collection<EntityModel> entities) {
        System.out.println("Collection size: " + entities.size());
        if (entities.size() != 0) {
            entities
                .stream()
                .map(entityModel -> {
                    String entityType = entityModel.getValue("type").getValue().toString().toUpperCase();
                    String[] printFieldNames = new String[]{"name", "text"};

                    for(String fieldName : printFieldNames) {
                        FieldModel printFieldModel = entityModel.getValue(fieldName);
                        if (printFieldModel != null) {
                            return entityType+":"+printFieldModel.getValue().toString();
                        }
                    }
                    return entityType;
                })
                .forEach(System.out::println);
        }
        System.out.println("-----------------------------");
    }

    /**
     * Method to simulate the return json format of the Octane server for a single entity
     * @param entityModel
     * @return
     */
    public static JSONObject getEntityJSONObject(EntityModel entityModel) {
        Set<FieldModel> fieldModels = entityModel.getValues();
        JSONObject objField = new JSONObject();
        fieldModels.forEach((field) -> objField.put(field.getName(), field.getValue().toString()));
        return objField;
    }

    /**
     * Method to simulate the return json format of the Octane server for a collection of entities
     * @param entitiesModels
     * @return
     */
    public static JSONObject getEntitiesJSONObject(Collection<EntityModel> entitiesModels) {
        JSONObject objBase = new JSONObject();
        JSONArray objEntities = new JSONArray();
        objBase.put("data", objEntities);
        objBase.put("count", entitiesModels.size());
        objBase.put("exceeds_total_count", false);
        entitiesModels.forEach((i) -> objEntities.put(getEntityJSONObject(i)));
        return objBase;

    }

}