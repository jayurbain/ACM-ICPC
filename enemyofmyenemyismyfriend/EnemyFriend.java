import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author jayurbain
 *
 */

public class EnemyFriend {

	public EnemyFriend() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		Scanner s1 = new Scanner(System.in);
		Scanner s2 = new Scanner(s1.nextLine());
		int nCases = s2.nextInt();
		s2 = new Scanner(s1.nextLine());
		int nRelationships = s2.nextInt();
		System.out.println("nRelationships = " + nRelationships);
		int nInvitationList = s2.nextInt();
		System.out.println("nInvitationList = " + nInvitationList);
		
		//
		if( nRelationships==0) {
			System.out.println("Invalid nRelationships");
			System.exit(0);
		}
		if( nInvitationList==0) {
			System.out.println("Invalid nInvitationList");
			System.exit(0);
		}
		
		int c=1;
		for(c=1; c<=nCases; c++) {
			
			List<ArrayList<String>> enemyList = new ArrayList<ArrayList<String>>();
			List<ArrayList<String>> friendList = new ArrayList<ArrayList<String>>();
			
			for(int i=0; i<nRelationships; i++) {
				
				s2 = new Scanner(s1.nextLine());
				String fore = s2.next();
				int n = s2.nextInt();
				ArrayList<String> list = new ArrayList<String>();
				for(int j=0; j<n; j++){
					String ss = s2.next();
					list.add(ss);
				}
				System.out.println(list);
				if(fore.equals("e")) {
					enemyList.add(list);
				}
				else {
					friendList.add(list);
				}
			}
			
			String resultString = "Case " + c + ": ";
			for(int i=0; i<nInvitationList; i++) {
				ArrayList<String> list = new ArrayList<String>();
				s2 = new Scanner(s1.nextLine());
				int cases = s2.nextInt();
				for(int j=0; j<cases; j++) {
					String ss = s2.next();
					list.add(ss);
				}
				
				boolean friend = true;
				for(int j=0; j<cases-1; j++) {
					for(int k=j+1; k<cases; k++) {
						friend = isFriend(list.get(j), list.get(k), 
								enemyList, friendList);
						if( !friend ) {
							System.out.println(list.get(j) + " " + list.get(k) + " enemies");
							friend = false;
							break;
							
						}
						else {
							System.out.println(list.get(j) + " " + list.get(k) + " friends");
						}
					}
					if( !friend ) {
						break;
					}
				}
				if( !friend  ) {
					resultString += " no";
				}
				else {
					resultString += " yes";
				}
				
			}
			System.out.println(resultString);
		}
	}
	
	public static boolean isFriend(String s1, String s2,
			List<ArrayList<String>> enemyList, List<ArrayList<String>> friendList) {
		
		for( ArrayList<String> list: enemyList) {
			if( list.contains(s1) && list.contains(s2)) {
				return false;
			}
		}
		
		for( ArrayList<String> list: friendList) {
			if( list.contains(s1) && list.contains(s2)) {
				return true;
			}
		}
		
		ArrayList<String> s1EnemyList = new ArrayList<String>();
		for( ArrayList<String> list: enemyList) {
			if( list.contains(s1) ) {
				for( String ss1 : list ) {
					if( !ss1.equals(s1) ) {
						s1EnemyList.add(ss1);
					}
				}
			}
		}
		
		ArrayList<String> s2EnemyList = new ArrayList<String>();
		for( ArrayList<String> list: enemyList) {
			if( list.contains(s2) ) {
				for( String ss2 : list ) {
					if( !ss2.equals(s2) ) {
						s2EnemyList.add(ss2);
					}
				}
			}
		}
		
		for(String s1Enemy: s1EnemyList) {
			if( s2EnemyList.contains(s1Enemy) ) {
				return true;
			}
		}
	
		return false;
	}

}
