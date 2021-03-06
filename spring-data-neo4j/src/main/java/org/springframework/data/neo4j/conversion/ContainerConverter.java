/**
 * Copyright 2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.neo4j.conversion;

import org.neo4j.helpers.collection.IteratorUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.*;

/**
 * @author mh
 * @since 11.11.11
 */
public class ContainerConverter {
    @SuppressWarnings("unchecked")
    public static <T, C extends Iterable<T>> C toContainer(Class<C> container, Iterable<T> data) {
        if (Iterable.class.equals(container)) return (C) data;
        if (SortedSet.class.isAssignableFrom(container)) return (C) IteratorUtil.addToCollection(data, new TreeSet<T>());
        if (Set.class.isAssignableFrom(container)) return (C) IteratorUtil.addToCollection(data, new HashSet<T>());
        List<T> list = IteratorUtil.addToCollection(data, new ArrayList<T>(50));
        if (Page.class.isAssignableFrom(container)) {
            return (C) new PageImpl<T>(list);
        }
        return (C) list;
    }
}
