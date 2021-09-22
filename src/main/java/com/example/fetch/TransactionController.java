package com.example.fetch;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//http://localhost:8080/balance?payer=man&points=1&timestamp=fdsfd

@RestController
@RequestMapping("/")
public class TransactionController {

	private static final String template = "Hello, %s!";
	
	
	ArrayList<SpendResponse> result;
	private ArrayList<Transaction> transactionlst = new ArrayList<Transaction>();
	private SpentPoints totalPts =  new SpentPoints(0);
	
	
	
	
	//for testing only so i dont have to painfully enter this manually everytime
	private void initArrlst() {
		transactionlst.add(new Transaction("DANNON", 1000, "2020-11-02T14:00:00Z" ));
		transactionlst.add(new Transaction("UNILEVER", 200, "2020-10-31T11:00:00Z" ));
		transactionlst.add(new Transaction("DANNON", -200, "2020-10-31T15:00:00Z" ));
		transactionlst.add(new Transaction("MILLER COORS", 10000, "2020-11-01T14:00:00Z" ));
		transactionlst.add(new Transaction("DANNON", 300, "2020-10-31T10:00:00Z" ));
		
	}
	
	
	
	//HELPERS
    private int searchPayer(ArrayList<SpendResponse> arr, String payer){
    	if(arr.size() != 0) {
    		for (int i = 0; i < arr.size(); i++) {
    			if(arr.get(i).getPayer().equals(payer)) {
    				return i;
    			}
    		}
    		return -1;
    	}
        return -1;
    }
    
    
    //generates new arr with the subtracted points
	private ArrayList<SpendResponse> processResponse(){
		int total = totalPts.getPts();
		
		
		//transactionlst = new ArrayList<Transaction>();
		//int total = 5000;
		//initArrlst();
		result = new ArrayList<SpendResponse>();
		
		
		Collections.sort(transactionlst);
		for (int i = 0; i < transactionlst.size(); i++) {
			if(total == 0) {
				return result;
			}
			String transPayer = transactionlst.get(i).getPayer();
			int transPoints = transactionlst.get(i).getPoints();
			int foundPayerIdx = searchPayer(result, transPayer);
			
			if(foundPayerIdx == -1  ) {//if payer not in new arraylist
				if(total >= transPoints) {
					result.add(new SpendResponse( transPayer , -transPoints ));
					total -= transPoints;
				}else {
					result.add(new SpendResponse( transPayer , -total ));
					total = 0;
				}
			}else {//if payer is in the new arrlist
				if(total >= transPoints) {
					result.set(foundPayerIdx, new SpendResponse(transPayer, 
							result.get(foundPayerIdx).getPoints() - transPoints));
					total -= transPoints;
				}else {
					return result;
				}
			}
			/*
			result.set(foundPayerIdx, new SpendResponse(transPayer, 
					result.get(foundPayerIdx).getPoints() - transPoints));
			total -= transPoints;
			*/
		}
		return result;
		
	}
	
	
	private HashMap<String, Integer> showBalance(){
		HashMap<String, Integer> map = new HashMap<String, Integer>();
		
		for (Transaction t : transactionlst) {
			if(map.containsKey(t.getPayer())) {
				int oldpts = map.get(t.getPayer());
				map.replace(t.getPayer(), oldpts + t.getPoints());
			}else {
				map.put(t.getPayer(),t.getPoints());
			}
		}
		return map;
		
	}
	
	
	
	//http://localhost:8080/balance?
	@GetMapping("/balance")
	public HashMap<String, Integer> balance() {
		HashMap<String, Integer> map =  showBalance();
		for (SpendResponse sr : result) {
			if(map.containsKey(sr.getPayer())) {
				int oldpts = map.get(sr.getPayer());
				map.replace(sr.getPayer(), oldpts + sr.getPoints());
			}
		}
		return map;
		
	}
	
	
	
	
	//http://localhost:8080/addtransaction?
	//type this: { "payer": "DANNON", "points": 1000, "timestamp": "2020-11-02T14:00:00Z" }
	@PostMapping(value = "/addtransaction")
	public ArrayList<Transaction> addBalance() {
		initArrlst();
		return transactionlst;
	}
	/*
	@PostMapping(value = "/addtransaction")
	public void addBalance(@RequestBody Transaction t) {
		transactionlst.add(t);
	}
	*/
	
	
	
	
	
	
	//http://localhost:8080/spendpoints?
	//type this: { "pts":5000 }
	@PostMapping(value = "/spendpoints")
	public ArrayList<SpendResponse> spendPoints(@RequestBody SpentPoints points) {
		totalPts = points;
		return processResponse();
	}
	/*
	@PostMapping(value = "/spendpoints")
	public SpentPoints spendPoints(@RequestBody SpentPoints points) {
		totalPts = points;
		return totalPts;
	}
	  */
	 
	
	@PostMapping(value = "/test")
    // consumes = MediaType.APPLICATION_JSON_VALUE, 
     //produces = MediaType.APPLICATION_JSON_VALUE)
	public ArrayList<SpendResponse> datetest(@RequestBody int points) {
		//spentPt = points;
		return processResponse();
	}
	

	
	@GetMapping(value = "/show")
	public ArrayList<Transaction> show() {
		
		Collections.sort(transactionlst);
		return transactionlst;
	}
	
	
	
	
	
	/*
	@GetMapping("/balance")
	public Transaction balance(@RequestParam(value = "payer") String payer,
							   @RequestParam(value = "points") int points, 
							   @RequestParam(value = "timestamp") String timestamp) {
		return new Transaction(payer, points, timestamp);
	}*/
	
}
