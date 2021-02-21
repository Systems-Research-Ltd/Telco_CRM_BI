package com.bitguiders.util.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.bitguiders.util.ResourceBundle;

public class Permission {

	private List<String> operationLabelsList;
	private List<Integer> operationCodesList;
	private List<Boolean> permissionsList;

	
	public void setOperationList(String... operations) {

		// C,E,D,X,E...
		operationLabelsList = new ArrayList<String>();

		// 1,2,4,8,16,32...
		operationCodesList = new ArrayList<Integer>();

		// true,false,true,false... 1001000
		permissionsList = new ArrayList<Boolean>();

		// List<Integer> operationCodesList = new ArrayList<Integer>();

		int tempCode = 2;
		// prepare lists with user operations
		for (String op : operations) {
			for (String p : op.split(",")) {
				operationLabelsList.add(p);

				// enter first hex code
				if (operationCodesList.size() == 0) {
					operationCodesList.add(1);
					tempCode = 1;
				} else {
					tempCode = tempCode * 2;
					operationCodesList.add(tempCode);
				}
			}// inner loop
		}
		Collections.sort(operationCodesList, Collections.reverseOrder());
	}

	public List<String> getOperationList() {
		return operationLabelsList;
	}

	public List<Integer> getOperationCodeList() {
		return operationCodesList;
	}

	// binary to hex 00000110 => 6
	private int getPermissionCode(String binaryCode) {
		System.out
				.println(binaryCode + " = " + Integer.parseInt(binaryCode, 2));
		return Integer.parseInt(binaryCode, 2);
	}

	public int getPermissionCode(int permissionCode, int index) {
		char permissions[] = decode(permissionCode);
		index = (permissions.length - 1) - index;
		permissions[index] = (permissions[index] == '1' ? '0' : '1');

		String newPermissionBinary = new String(permissions);

		// System.out.println("index = "+index+" Old code = "+permissionCode
		// +" New Code = "+newPermissionBinary);
		return getPermissionCode(newPermissionBinary);
	}

	// hex-> binary 6 => 00000110
	private char[] decode(int hexNo) {
		return getDecodedBinaryString(hexNo).toCharArray();
	}

	private String getDecodedBinaryString(int hexNo) {

		// maxBinary calculated from given no of services
		String maxBinaryValue = Integer.toBinaryString(operationCodesList
				.get(0));
		
		// set all values to 0 so that we can append these values into new
		// conversion to complete length
		maxBinaryValue.replace('1', '0');

		// converte no into binary 110
		String binaryString = Integer.toBinaryString(hexNo);

		// merge missing 0 on left to complete length 0000 <- 110
		binaryString = maxBinaryValue.substring(binaryString.length(),
				maxBinaryValue.length()) + binaryString;

		System.out.println(hexNo + " = " + binaryString);

		return binaryString;
	}

	public List<Boolean> getPermissionList(int permissionCode) {
		char permissions[] = decode(permissionCode);
		permissionsList.clear();
		for (char c : permissions) {
			permissionsList.add((c == '1' ? true : false));
		}
		return permissionsList;
	}
	
   //C,R,U,D,S,A
	public boolean isCreate(){
		return this.isPermission("C");
	}
	public boolean isRead(){
        return this.isPermission("R");
    }
	public boolean isUpdate(){
        return this.isPermission("U");
    }
	public boolean isDelete(){
		return this.isPermission("D");
	}
	public boolean isShare(){
		return this.isPermission("S");
	}
	public boolean isAssign(){
		return this.isPermission("A");
	}
	public boolean isPermission(String operation){
		int index=0;
		for(String operationLabel:operationLabelsList){
			
			if(operationLabel.equalsIgnoreCase(operation)){
				return (permissionsList.size()>index?permissionsList.get(index):false);
			}
			index++;
		}
		return false;
	}
	public boolean isPermission(int operationCode,String operation){
		getPermissionList(operationCode);
		int index=0;
		for(String operationLabel:operationLabelsList){
			
			if(operationLabel.equalsIgnoreCase(operation)){
				return (permissionsList.size()>index?permissionsList.get(index):false);
			}
			index++;
		}
		return false;
	}
	
	public int getPersmissionCode(List<Integer> permissionCodes){
		char[] permissions = null;
		for(int p: permissionCodes){
			char temp[] = decode(p);
			if(permissions == null){
				permissions = temp;
			} else {
				for(int i = 0; i < temp.length; i++){
					if(permissions[i] == '0' && temp[i] == '1'){
						permissions[i] = '1';
					}
				}
			}	
			
		}
		String binaryCode = "";
		for(int i=0; i<permissions.length; i++){
			binaryCode +=  permissions[i];
		}
		return getPermissionCode(binaryCode);
	}

	public static void main(String arg[]) {

		Permission permission = new Permission();
		permission.setOperationList(ResourceBundle.getInstance()
				.getPropertyValue("secure.operations"));

		for (String label : permission.getOperationList()) {
			System.out.print(label + " , ");
		}
		System.out.println(" ");
		for (Integer code : permission.getOperationCodeList()) {
			System.out.print(code + " , ");
		}
		List<Integer> test = new ArrayList<Integer>();
		test.add(16);
		System.out.println("----------"+permission.getPermissionList(60));
		permission.getPermissionCode("001");

		/*// on the base of permission get on/of check boxes
		for (Boolean b : permission.getPermissionList(8)) {
			System.out.print(b + " - ");
		}
		System.out.println("");
		System.out.println(permission.isRead());
		System.out.println(permission.isDelete());
		System.out.println(permission.isPermission(12,"delete"));
		System.out.println(permission.isPermission("View"));
		//System.out.println(permission.getPermissionCode(8, 1));

		Class c = permission.getClass();
		System.out.println(c.getName());*/
	}
}
