package com.clark.nfc.plugin;

import com.nxp.nfclib.classic.IMFClassic;
import com.nxp.nfclib.exceptions.PlusException;
import com.nxp.nfclib.exceptions.ReaderException;
import com.nxp.nfclib.exceptions.SAMException;
import com.nxp.nfclib.exceptions.SmartCardException;
import com.nxp.nfclib.icode.ICodeSLI;
import com.nxp.nfclib.icode.IICodeSLI;
import com.nxp.nfclib.icode.IICodeSLIL;
import com.nxp.nfclib.icode.IICodeSLIS;
import com.nxp.nfclib.icode.IICodeSLIX;
import com.nxp.nfclib.icode.IICodeSLIX2;
import com.nxp.nfclib.icode.IICodeSLIXL;
import com.nxp.nfclib.icode.IICodeSLIXS;
import com.nxp.nfclib.ndef.FormatException;
import com.nxp.nfclib.ndef.NdefMessage;
import com.nxp.nfclib.ndef.NdefRecord;
import com.nxp.nfclib.ntag.INTag;
import com.nxp.nfclib.ntag.INTag203x;
import com.nxp.nfclib.ntag.INTag210;
import com.nxp.nfclib.ntag.INTag213215216;
import com.nxp.nfclib.ntag.INTag213F216F;
import com.nxp.nfclib.ntag.INTagI2C;
import com.nxp.nfclib.plus.IPlusSL1;
import com.nxp.nfclib.ultralight.IUltralight;
import com.nxp.nfclib.ultralight.IUltralightC;
import com.nxp.nfclib.ultralight.IUltralightEV1;
import com.nxp.nfclib.utils.NxpLogUtils;
import com.nxp.nfclib.utils.Utilities;
import com.nxp.nfcliblite.Interface.NxpNfcLibLite;
import com.nxp.nfcliblite.Interface.Nxpnfcliblitecallback;
import com.nxp.nfcliblite.cards.IDESFireEV1;
import com.nxp.nfcliblite.cards.IPlus;


import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.apache.cordova.PluginResult.Status;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;


public class MifarePlugin extends CordovaPlugin {
    	private NxpNfcLibLite libInstance = null;
    	private IDESFireEV1 mDESFire;
     private static final String LOGTAG = "MifarePlugin";
    private static final String ACTION_INIT = "init";
     private static final String ACTION_AUTHENTICATE = "Authenticate";
    private static final String ACTION_ENCRYPT = "Encrypt";
     private static final String ACTION_DECRYPT = "Decrypt";  
     private static final String ACTION_PERSONALIZE = "Personalize";  
   private static final String ACTION_UPDATEMASTERKEY = "Updatemasterkey";  
   private static final String ACTION_WRITE_TAG_DATA = "writeTag";  
	private byte[] bytesKey = null;
	/** */
	private Cipher cipher = null;
	/** */
	private IvParameterSpec iv = null;

    
        public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
            
            
  	showMessage("Card Detected : " + mDESFire.getCardDetails().cardName,
				'n');
        
            
            
            
              if (ACTION_INIT.equals(action)) {
            result = init(args.getJSONObject(0), callbackContext);
        } else if (ACTION_WRITE_TAG_DATA.equals(action)) {
            result = writeTag(args.getJSONObject(0), callbackContext);
        } else if (ACTION_AUTHENTICATE.equals(action)) {

     result = authenticate(args.getJSONObject(0), callbackContext);
            
 }else if (ACTION_ENCRYPT.equals(action)) {

     result = encrypt(args.getJSONObject(0), callbackContext);
            
              
              }
      
            
    }

           return true;
    
   
    
}


  private void init(CallbackContext callbackContext) {
        Log.d(TAG, "Enabling plugin " + getIntent());

        startNfc();
        if (!recycledIntent()) {
            parseMessage();
        }
        callbackContext.success();
    }



    private void registerDefaultTag(CallbackContext callbackContext) {
        
      
        
      addTagFilter();
      callbackContext.success();
  }














