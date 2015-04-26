<g:hiddenField name="manipulateReg" value="${createLink(controller: 'admin', action: 'manipulateReg')}"/>
<g:hiddenField name="manipulateLock" value="${createLink(controller: 'admin', action: 'manipulateFieldLock')}"/>
<g:hiddenField name="manipulateMand" value="${createLink(controller: 'admin', action: 'manipulateFieldMand')}"/>
<g:hiddenField name="manipulatePermission"
               value="${createLink(controller: 'user', action: 'manipulateFieldVisibility')}"/>
<g:hiddenField name="addInterestURL" value="${createLink(controller: 'interests', action: 'addInterest')}"/>
<g:hiddenField name="referenceVoteURL" value="${createLink(controller: 'interests', action: 'referenceVote')}"/>
<g:hiddenField name="loadEntityURL" value="${createLink(controller: 'autoComplete', action: 'loadEntity')}"/>