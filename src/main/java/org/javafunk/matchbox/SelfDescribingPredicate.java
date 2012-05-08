/*
 * Copyright (C) 2012 Matchbox committers.
 * All rights reserved.
 *
 * The software in this package is published under the terms of the BSD
 * style license a copy of which has been included with this distribution in
 * the LICENSE.txt file.
 */
package org.javafunk.matchbox;

import org.javafunk.funk.behaviours.Describable;
import org.javafunk.funk.functors.Predicate;

public interface SelfDescribingPredicate<T> extends Predicate<T>, Describable {}
