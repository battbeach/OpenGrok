/*
 * CDDL HEADER START
 *
 * The contents of this file are subject to the terms of the
 * Common Development and Distribution License (the "License").  
 * You may not use this file except in compliance with the License.
 *
 * See LICENSE.txt included in this distribution for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing Covered Code, include this CDDL HEADER in each
 * file and include the License file at LICENSE.txt.
 * If applicable, add the following below this CDDL HEADER, with the
 * fields enclosed by brackets "[]" replaced with your own identifying
 * information: Portions Copyright [yyyy] [name of copyright owner]
 *
 * CDDL HEADER END
 */

/*
 * Copyright 2005 Sun Microsystems, Inc.  All rights reserved.
 * Use is subject to license terms.
 */

/*
 * ident	"@(#)CompatibleAnalyser.java 1.1     05/11/11 SMI"
 */

package org.opensolaris.opengrok.analysis;

import java.io.*;
import org.apache.lucene.analysis.*;
import org.opensolaris.opengrok.analysis.plain.PlainFullTokenizer;
import org.opensolaris.opengrok.analysis.plain.PlainSymbolTokenizer;

public class CompatibleAnalyser extends Analyzer {
    PathAnalyzer pather;
    HistoryAnalyzer historer;
    
    public CompatibleAnalyser() {
        historer = new HistoryAnalyzer();
        pather  = new PathAnalyzer();
    }
    
    public TokenStream tokenStream(String fieldName, Reader reader) {
        if (fieldName.equals("full")) {
            return new PlainFullTokenizer(reader);
        } else if (fieldName.equals("refs")) {
            return new PlainSymbolTokenizer(reader);
        } else if (fieldName.equals("defs")) {
            return new PlainSymbolTokenizer(reader);
        } else if (fieldName.equals("path")) {
            return pather.tokenStream(fieldName, reader);
        } else if (fieldName.equals("hist")) {
            return historer.tokenStream(fieldName, reader);
        }
        return new PlainFullTokenizer(reader);
    }
}