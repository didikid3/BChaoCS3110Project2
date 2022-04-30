import java.lang.Math;
public class floatPointValue {
	
	
	public double[] isValid(String x)
	{
		double[] resultArray = new double[2];
		resultArray[0] = -1;//Value  -1 represents failed
		resultArray[1] = 0;//Char count
		
		int size = x.length();
		int state = 1;
		
		char letter;
		int asciiValue;
		
		double value = 0.0;
		int decimal = 0;
		
		int exponent = 0;
		boolean exponentSign = false;
		
		for(int i = 0; i < size; i++) {
			letter = x.charAt(i);
			asciiValue = (int) letter;
			resultArray[1] = resultArray[1] + 1;
			
			switch(state) {
				case 1:
					if(asciiValue >= 48 && asciiValue <= 57) {
						state = 2;
						
						value = (value*10) + (asciiValue-48);
					}
					else if( asciiValue == 46 )
					{
						state = 3;
					}
					else
						return resultArray;
					break;
				case 2:
					if(asciiValue >= 48 && asciiValue <= 57) {
						state = 2;
						
						value = (value*10) + (asciiValue-48);
					}
					else if(asciiValue == 68 || asciiValue == 70 ||
							asciiValue == 100 || asciiValue == 102) {
						state = 5;
					}
					else if( asciiValue == 46 )
					{
						state = 7;
					}
					else if(asciiValue == 95) {
						state = 4;
					}
					else if(asciiValue == 69 || asciiValue == 101) {
						state = 9;
					}
					else
						return resultArray;
					break;
				case 3:
					if(asciiValue >= 48 && asciiValue <= 57) {
						state = 6;
						
						value = (value*10) + (asciiValue-48);
						decimal += 1;
					}
					else
						return resultArray;
					break;
				case 4:
					if(asciiValue == 95) {
						state = 4;
					}
					else if(asciiValue >= 48 && asciiValue <= 57) {
						state = 2;
						
						value = (value*10) + (asciiValue-48);
					}
					else
						return resultArray;
					break;
				//Modified to handle expressions
					
				//Previously was just "FfDd" must be last character in string
				//Now is, only accepts "+-*/"
				case 5:
					//Modified for expressions
					if(asciiValue == 41 || asciiValue == 42 || asciiValue == 43 ||
							asciiValue == 45 || asciiValue == 47) {
						i = size;
						/*
						if(asciiValue != 41)
							resultArray[1] = resultArray[1] + 1;
							*/
					}
					else{
						return resultArray;
					}
					break;
				//Modified to handle expressions
				case 6:
					if(asciiValue >= 48 && asciiValue <= 57) {
						state = 6;
						
						value = (value*10) + (asciiValue-48);
						decimal += 1;
					}
					//Modified for expressions
					else if(asciiValue == 41 || asciiValue == 42 || asciiValue == 43 ||
							asciiValue == 45 || asciiValue == 47) {
						i = size;
					}
					else if(asciiValue == 95) {
						state = 8;
					}
					else if(asciiValue == 68 || asciiValue == 70 ||
							asciiValue == 100 || asciiValue == 102) {
						state = 5;
					}
					else if(asciiValue == 69 || asciiValue == 101) {
						state = 13;
					}
					else
						return resultArray;
					break;
				//Modified to handle expressions
				case 7:
					if(asciiValue >= 48 && asciiValue <= 57) {
						state = 6;
						
						value = (value*10) + (asciiValue-48);
						decimal += 1;
					}
					else if(asciiValue == 69 || asciiValue == 101) {
						state = 13;
					}
					else if(asciiValue == 68 || asciiValue == 70 ||
							asciiValue == 100 || asciiValue == 102) {
						state = 5;
					}
					//Modified for expressions
					else if(asciiValue == 41 || asciiValue == 42 || asciiValue == 43 ||
							asciiValue == 45 || asciiValue == 47) {
						i = size;
					}
					else
						return resultArray;
					break;
				case 8:
					if(asciiValue == 95) {
						state = 8;
					}
					else if(asciiValue >= 48 && asciiValue <= 57) {
						state = 6;
						
						value = (value*10) + (asciiValue-48);
						decimal += 1;
					}
					else
						return resultArray;
					break;
				case 9:
					if(asciiValue >= 48 && asciiValue <= 57) {
						state = 11;
						
						exponent = (exponent*10) + (asciiValue-48);
					}
					else if(asciiValue == 43 || asciiValue == 45) {
						state = 10;
						
						if(asciiValue == 45) {
							exponentSign = true;
						}
					}
					else
						return resultArray;
					break;
				case 10:
					if(asciiValue >= 48 && asciiValue <= 57) {
						state = 11;
						
						exponent = (exponent*10) + (asciiValue-48);
					}
					else
						return resultArray;
					break;
				//Modified to handle expressions
				case 11:
					if(asciiValue >= 48 && asciiValue <= 57) {
						state = 11;
						
						exponent = (exponent*10) + (asciiValue-48);
					}
					//Modified for expressions
					else if(asciiValue == 41 || asciiValue == 42 || asciiValue == 43 ||
							asciiValue == 45 || asciiValue == 47) {
						i = size;
					}
					else if(asciiValue == 95) {
						state = 12;
					}
					else if(asciiValue == 68 || asciiValue == 70 ||
							asciiValue == 100 || asciiValue == 102) {
						state = 5;
					}
					else
						return resultArray;
					break;
				case 12:
					if(asciiValue == 95) {
						state = 12;
					}
					else if(asciiValue >= 48 && asciiValue <= 57) {
						state = 11;
						
						exponent = (exponent*10) + (asciiValue-48);
					}
					else
						return resultArray;
					break;
				case 13:
					if(asciiValue >= 48 && asciiValue <= 57) {
						state = 15;
						
						exponent = (exponent*10) + (asciiValue-48);
					}
					else if(asciiValue == 43 || asciiValue == 45) {
						state = 14;
						
						if(asciiValue == 45) {
							exponentSign = true;
						}
					}
					else
						return resultArray;
					break;
				case 14:
					if(asciiValue >= 48 && asciiValue <= 57) {
						state = 15;
						
						exponent = (exponent*10) + (asciiValue-48);
					}
					else
						return resultArray;
					break;
				//Modified to handle expressions
				case 15:
					if(asciiValue >= 48 && asciiValue <= 57) {
						state = 15;
						
						exponent = (exponent*10) + (asciiValue-48);
					}
					//Modified for expressions
					else if(asciiValue == 41 || asciiValue == 42 || asciiValue == 43 ||
							asciiValue == 45 || asciiValue == 47) {
						i = size;
					}
					else if(asciiValue == 68 || asciiValue == 70 ||
							asciiValue == 100 || asciiValue == 102) {
						state = 5;
					}
					else if(asciiValue == 95) {
						state = 16;
					}
					else
						return resultArray;
					break;
				case 16:
					if(asciiValue == 95) {
						state = 16;
					}
					else if(asciiValue >= 48 && asciiValue <= 57) {
						state = 15;
							
						exponent = (exponent*10) + (asciiValue-48);
					}
					else
						return resultArray;
					break;
			}
			if(i == size-1)
				resultArray[1] += 1;
		}
		if(		state == 1 ||
				state == 2 || 
				state == 3 || state == 4 ||
				state == 8 || state == 9 ||
				state == 10 || state == 12 ||
				state == 13 || state == 14 ||
				state == 16
		) {
			return resultArray;
		}

		value = value * Math.pow(10, -1* decimal);
		if(exponentSign)
			value = value * Math.pow(10,-1* exponent);
		else
			value = value * Math.pow(10, exponent);
		
		resultArray[0] = value;
		
		//Iterates over "invalid chars" So must delete from count
		//Must make sure expressions handles invalid
		resultArray[1] = resultArray[1] - 1;
		return resultArray;
	}
	

}
