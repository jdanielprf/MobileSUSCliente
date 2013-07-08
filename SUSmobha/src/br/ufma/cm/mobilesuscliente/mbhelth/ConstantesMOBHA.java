package br.ufma.cm.mobilesuscliente.mbhelth;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import br.ufma.cm.mobilesuscliente.TelaConfig;

public class ConstantesMOBHA {
	public static String idUM = "e1u2";
	public static String central = "e1u1";
	
	
	public static void lerID(){
		try {
			FileInputStream inp = new FileInputStream(TelaConfig.diretorio + "dadosID.txt");
		
			Scanner s = new Scanner(inp);

			
			if(s.hasNextLine()){
				idUM=(s.nextLine());
			}
		
			if(s.hasNextLine()){
				central=(s.nextLine());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
