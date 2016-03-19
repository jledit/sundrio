/*
 * Copyright 2015 The original authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package io.sundr.dsl.internal.processor;

import io.sundr.Function;
import io.sundr.codegen.converters.ExecutableElementToJavaMethod;
import io.sundr.codegen.converters.StringToJavaType;
import io.sundr.codegen.converters.VariableElementToJavaProperty;
import io.sundr.codegen.model.JavaProperty;
import io.sundr.codegen.model.JavaType;
import io.sundr.dsl.internal.element.functions.ToKeywords;
import io.sundr.dsl.internal.element.functions.ToRequiresAll;
import io.sundr.dsl.internal.element.functions.ToRequiresAny;
import io.sundr.dsl.internal.element.functions.ToRequiresNoneOf;
import io.sundr.dsl.internal.element.functions.ToRequiresOnly;
import io.sundr.dsl.internal.graph.functions.ToNext;
import io.sundr.dsl.internal.graph.functions.ToRoot;
import io.sundr.dsl.internal.graph.functions.ToTransition;
import io.sundr.dsl.internal.graph.functions.ToTree;
import io.sundr.dsl.internal.graph.functions.ToGraph;
import io.sundr.dsl.internal.graph.functions.ToScopes;

import javax.lang.model.element.VariableElement;
import javax.lang.model.util.Elements;
import javax.lang.model.util.Types;

public class DslProcessorContext {

    private final Elements elements;
    private final Function<String, JavaType> toType;
    private final Function<VariableElement, JavaProperty> toProperty;
    private final ExecutableElementToJavaMethod toMethod;
    private final ToRequiresAny toRequiresAny;
    private final ToRequiresAll toRequiresAll;
    private final ToRequiresNoneOf toRequiresNoneOf;
    private final ToRequiresOnly toRequiresOnly;
    private final ToKeywords toKeywords;
    private final ToNext toNext;
    private final ToTree toTree;
    private final ToGraph toGraph;

    private final ToRoot toRoot;
    private final ToTransition toTranstion;

    private final ToScopes toScopes;

    private final DslRepository repository = new DslRepository();

    public DslProcessorContext(Elements elements, Types types) {
        this.elements = elements;
        this.toType = new StringToJavaType(elements);
        this.toProperty = new VariableElementToJavaProperty(toType);
        this.toMethod = new ExecutableElementToJavaMethod(toType, toProperty);
        this.toRequiresAny = new ToRequiresAny(elements);
        this.toRequiresAll = new ToRequiresAll(elements);
        this.toRequiresNoneOf = new ToRequiresNoneOf(elements);
        this.toRequiresOnly = new ToRequiresOnly(elements);
        this.toKeywords = new ToKeywords(elements);
        this.toNext = new ToNext();
        this.toTree = new ToTree(toNext);
        this.toGraph = new ToGraph(toTree);
        this.toTranstion = new ToTransition(repository);
        this.toRoot = new ToRoot(repository, toTranstion);
        this.toScopes = new ToScopes(repository, toTranstion, toTree);
    }

    public Elements getElements() {
        return elements;
    }

    public Function<String, JavaType> getToType() {
        return toType;
    }

    public ExecutableElementToJavaMethod getToMethod() {
        return toMethod;
    }

    public ToRequiresAny getToRequiresAny() {
        return toRequiresAny;
    }

    public ToRequiresAll getToRequiresAll() {
        return toRequiresAll;
    }

    public ToRequiresNoneOf getToRequiresNoneOf() {
        return toRequiresNoneOf;
    }

    public ToRequiresOnly getToRequiresOnly() {
        return toRequiresOnly;
    }

    public ToKeywords getToKeywords() {
        return toKeywords;
    }

    public ToNext getToNext() {
        return toNext;
    }

    public ToTree getToTree() {
        return toTree;
    }

    public ToGraph getToGraph() {
        return toGraph;
    }

    public ToRoot getToRoot() {
        return toRoot;
    }

    public ToTransition getToTranstion() {
        return toTranstion;
    }

    public ToScopes getToScopes() {
        return toScopes;
    }

    public DslRepository getRepository() {
        return repository;
    }
}
