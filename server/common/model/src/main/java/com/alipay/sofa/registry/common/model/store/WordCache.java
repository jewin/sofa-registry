/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alipay.sofa.registry.common.model.store;

import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author shangyu.wh
 * @version $Id: WordCache.java, v 0.1 2018-11-06 12:01 shangyu.wh Exp $
 */
public class WordCache {

    private static volatile WordCache instance;

    /**
     * get WordCache instance
     * @return
     */
    public static WordCache getInstance() {
        if (instance == null) {
            synchronized (WordCache.class) {
                if (instance == null) {
                    instance = new WordCache();
                }
            }
        }
        return instance;
    }

    /**
     * word cache map
     */
    private ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

    /**
     *
     * @param s
     * @return String
     */
    public String getWordCache(String s) {
        if (s == null) {
            return null;
        }
        String oldValue = map.putIfAbsent(s, s);
        return oldValue == null ? s : oldValue;
    }

}