<g:hiddenField name="manipulateReg" value="${createLink(controller: 'admin', action: 'manipulateReg')}"/>
<g:hiddenField name="manipulateLock" value="${createLink(controller: 'admin', action: 'manipulateFieldLock')}"/>
<g:hiddenField name="manipulateMand" value="${createLink(controller: 'admin', action: 'manipulateFieldMand')}"/>
<g:hiddenField name="manipulatePermission"
               value="${createLink(controller: 'user', action: 'manipulateFieldVisibility')}"/>
<g:hiddenField name="addInterestURL" value="${createLink(controller: 'user', action: 'addInterest')}"/>