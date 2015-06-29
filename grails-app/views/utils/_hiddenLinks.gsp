<g:hiddenField name="manipulateReg" value="${createLink(controller: 'admin', action: 'manipulateReg')}"/>
<g:hiddenField name="manipulateLock" value="${createLink(controller: 'admin', action: 'manipulateFieldLock')}"/>
<g:hiddenField name="manipulateMand" value="${createLink(controller: 'admin', action: 'manipulateFieldMand')}"/>
<g:hiddenField name="manipulatePermission"
               value="${createLink(controller: 'user', action: 'manipulateFieldVisibility')}"/>
<g:hiddenField name="addInterestURL" value="${createLink(controller: 'interests', action: 'addInterest')}"/>
<g:hiddenField name="referenceVoteURL" value="${createLink(controller: 'interests', action: 'referenceVote')}"/>
<g:hiddenField name="loadEntityURL" value="${createLink(controller: 'autoComplete', action: 'loadEntity')}"/>
<g:hiddenField name="loadAuthorRefs" value="${createLink(controller: 'autoComplete', action: 'loadAuthorRefs')}"/>
<g:hiddenField name="loadAuthorRefsDetails"
               value="${createLink(controller: 'autoComplete', action: 'loadRefAuthorDetails')}"/>
<g:hiddenField name="mergeRefDialogURL" value="${createLink(controller: 'admin', action: 'openMergeRefDialog')}"/>
<g:hiddenField name="mergeDialogURL" value="${createLink(controller: 'admin', action: 'openMergeDialog')}"/>
<g:hiddenField name="orderAuthorsURL" value="${createLink(controller: 'reference', action: 'reorderAuthors')}"/>
