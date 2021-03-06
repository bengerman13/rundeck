/*
 * Copyright 2016 SimplifyOps, Inc. (http://simplifyops.com)
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

package rundeck.services.scm

import com.dtolabs.rundeck.core.authorization.UserAndRolesAuthContext
import com.dtolabs.rundeck.plugins.scm.ImportResult
import com.dtolabs.rundeck.plugins.scm.JobImporter
import com.dtolabs.rundeck.plugins.scm.ScmOperationContext

/**
 * Resolves a context and context job importer into a job importer
 */
class ResolvedJobImporter implements JobImporter {
    final ScmOperationContext context
    ContextJobImporter jobImporter

    ResolvedJobImporter(
            final ScmOperationContext context,
            final ContextJobImporter jobImporter
    )
    {
        this.context=context
        this.jobImporter = jobImporter
    }

    @Override
    ImportResult importFromStream(final String format, final InputStream input, final Map importMetadata) {
        return jobImporter.importFromStream(context, format, input, importMetadata)

    }

    @Override
    ImportResult importFromMap(final Map input, final Map importMetadata) {
        return jobImporter.importFromMap(context, input, importMetadata)
    }
}
