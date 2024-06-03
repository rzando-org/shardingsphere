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

package org.apache.shardingsphere.mode.path.rule.item;

import org.apache.shardingsphere.mode.path.rule.root.RuleRootNodePath;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

class UniqueRuleItemNodePathTest {
    
    @Test
    void assertPathWithNullParentNode() {
        UniqueRuleItemNodePath uniqueRuleItemNodePath = new UniqueRuleItemNodePath(new RuleRootNodePath("foo"), "test_path");
        assertThat(uniqueRuleItemNodePath.getPath(), is("test_path"));
    }
    
    @Test
    void assertGetPathWithParentNode() {
        UniqueRuleItemNodePath uniqueRuleItemNodePath = new UniqueRuleItemNodePath(new RuleRootNodePath("foo"), "test_parent", "test_path");
        assertThat(uniqueRuleItemNodePath.getPath(), is("test_parent/test_path"));
    }
    
    @Test
    void assertIsValidPathWithNullParentNode() {
        UniqueRuleItemNodePath uniqueRuleItemNodePath = new UniqueRuleItemNodePath(new RuleRootNodePath("foo"), "test_path");
        assertTrue(uniqueRuleItemNodePath.isValidatedPath("/word1/word2-/rules/foo/test_path/versions/1234"));
    }
    
    @Test
    void assertIsNotValidPathWithNullParentNode() {
        UniqueRuleItemNodePath uniqueRuleItemNodePath = new UniqueRuleItemNodePath(new RuleRootNodePath("foo"), "test_path");
        assertFalse(uniqueRuleItemNodePath.isValidatedPath("/word1/word2/rules/test_foo/test_path/versions/1234"));
        assertFalse(uniqueRuleItemNodePath.isValidatedPath("/rules/test_foo/test/versions/1234"));
        assertFalse(uniqueRuleItemNodePath.isValidatedPath("/word1/word2/rules/foo/test_path/versions/"));
    }
    
    @Test
    void assertIsActiveVersionPath() {
        UniqueRuleItemNodePath uniqueRuleItemNodePath = new UniqueRuleItemNodePath(new RuleRootNodePath("foo"), "test_path");
        assertTrue(uniqueRuleItemNodePath.isActiveVersionPath("/word1-/word2/rules/foo/test_path/active_version"));
    }
    
    @Test
    void assertIsNotActiveVersionPath() {
        UniqueRuleItemNodePath uniqueRuleItemNodePath = new UniqueRuleItemNodePath(new RuleRootNodePath("foo"), "test_path");
        assertFalse(uniqueRuleItemNodePath.isActiveVersionPath("/word1/word2/rules/foo/test_path/active_version1"));
        assertFalse(uniqueRuleItemNodePath.isActiveVersionPath("/rules/foo/test_path/active_version"));
    }
}
