/*
 * Copyright (C) 2011 Benoit GUEROUT <bguerout at gmail dot com> and Yves AMSELLEM <amsellem dot yves at gmail dot com>
 *
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
 */

package org.jongo;

import org.junit.Before;
import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;
import static org.jongo.util.TestUtil.getDatabase;

public class JongoTest {

    private Jongo jongo;

    @Before
    public void setUp() throws Exception {
        jongo = new Jongo(getDatabase());
    }

    @Test
    public void canObtainACollection() throws Exception {

        MongoCollection collection = jongo.getCollection("collection-name");

        assertThat(collection).isNotNull();
        assertThat(collection.getName()).isEqualTo("collection-name");
        assertThat(collection.getDBCollection().getFullName()).isEqualTo("jongo.collection-name");
    }
}