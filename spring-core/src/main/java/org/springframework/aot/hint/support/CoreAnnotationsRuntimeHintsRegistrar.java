/*
 * Copyright 2002-2022 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.aot.hint.support;

import java.util.stream.Stream;

import org.springframework.aot.hint.RuntimeHints;
import org.springframework.aot.hint.RuntimeHintsRegistrar;
import org.springframework.core.annotation.AliasFor;
import org.springframework.core.annotation.Order;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;

/**
 * {@link RuntimeHintsRegistrar} for core annotations.
 *
 * @author Phillip Webb
 * @since 6.0
 */
class CoreAnnotationsRuntimeHintsRegistrar implements RuntimeHintsRegistrar {

	@Override
	public void registerHints(RuntimeHints hints, @Nullable ClassLoader classLoader) {
		Stream.of(AliasFor.class, Order.class).forEach(annotationType ->
				RuntimeHintsUtils.registerAnnotation(hints, annotationType));
		if (ClassUtils.isPresent("jakarta.inject.Inject", classLoader)) {
			Stream.of("jakarta.inject.Inject", "jakarta.inject.Qualifier").forEach(annotationType ->
					RuntimeHintsUtils.registerAnnotation(hints, ClassUtils.resolveClassName(annotationType, classLoader)));
		}
	}

}
