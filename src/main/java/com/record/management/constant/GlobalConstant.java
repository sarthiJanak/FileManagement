package com.record.management.constant;

public class GlobalConstant {
	
	private GlobalConstant() {
		
	}
	public static final String COMMA = ",";
	public static final String ROLE_NAME_ADMIN = "Admin";
	public static final String ROLE_NAME_USER = "User";
	public static final String ENDPOINT_FILE_UPLOAD_URL = "/records/api/v1/file/upload";
	public static final String ENDPOINT_FILE_VIEW_URL = "/records/api/v1/file/getAllFiles";
	public static final String ENDPOINT_USER_MANAGEMENT_URL = "/user/api/v1/**";
	public static final String ROLE_NAME_ADMIN_AND_USER = ROLE_NAME_ADMIN.concat(COMMA).concat(ROLE_NAME_USER);
	public static final String FILE_TYPE_OPEN_EXCEL = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	public static final String FILE_TYPE_EXCEL = "application/vnd.ms-excel";
	
}
