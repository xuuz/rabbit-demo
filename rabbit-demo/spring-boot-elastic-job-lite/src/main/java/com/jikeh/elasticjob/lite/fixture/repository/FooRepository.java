/**
 * 极客慧 www.jikeh.cn
 * 头条号：https://www.toutiao.com/c/user/52045142300/
 */

/*
 * Copyright 1999-2015 dangdang.com.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package com.jikeh.elasticjob.lite.fixture.repository;

import com.jikeh.elasticjob.lite.fixture.entity.Foo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class FooRepository {
    
    private Map<Long, Foo> data = new ConcurrentHashMap<>(400, 1);
    
    public FooRepository() {
        init();
    }

    //初始化400条数据：
    private void init() {
        addData(0L, 100L, "Beijing");
        addData(100L, 200L, "Shanghai");
        addData(200L, 300L, "Guangzhou");
        addData(300L, 400L, "HangZhou");
    }
    
    public List<Foo> findTodoData(final String location, final int limit) {
        List<Foo> result = new ArrayList<>(limit);
        int count = 0;
        for (Map.Entry<Long, Foo> each : data.entrySet()) {
            Foo foo = each.getValue();
            if (foo.getLocation().equals(location) && foo.getStatus() == Foo.Status.TODO) {
                result.add(foo);
                count++;
                if (count == limit) {
                    break;
                }
            }
        }
        return result;
    }

    private void addData(final long idFrom, final long idTo, final String location) {
        for (long i = idFrom; i < idTo; i++) {
            data.put(i, new Foo(i, location, Foo.Status.TODO));
        }
    }
    
    public void setCompleted(final long id) {
        data.get(id).setStatus(Foo.Status.COMPLETED);
    }
}
