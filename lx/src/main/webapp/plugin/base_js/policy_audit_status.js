/**
 * 政策审批状态
 * 
 * @returns {Array}
 */
function get_policy_audit_status()
{
	var status = [
	   {id:POLICY_AUDIT_STATUS_ALL, name:'全部'},
		{id:POLICY_AUDIT_STATUS_DEFAULT, name:'未审批'},
		{id:POLICY_AUDIT_STATUS_BAD, name:'审批不通过'},
		{id:POLICY_AUDIT_STATUS_GOOD, name:'审批通过'}
	];
	
	return status;
}