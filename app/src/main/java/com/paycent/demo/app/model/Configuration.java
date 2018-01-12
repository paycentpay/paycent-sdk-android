package com.paycent.demo.app.model;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.paycent.demo.app.R;
import com.paycent.android.sdk.SdkPayRequest;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Configuration {

	private String TAG = this.getClass().getSimpleName();

	@SerializedName("channels")
	private String[] channels;

	@SerializedName("merchantNo")
	private String merchantNo;

	@SerializedName("merchantKey")
	private String merchantKey;

	@SerializedName("host")
	private String host;

	@SerializedName("payServerUrl")
	private String payServerUrl;

	@SerializedName("language")
	private String language;

	@SerializedName("country")
	private String country;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String[] getChannels() {
		return channels;
	}

	public void setChannels(String[] channels) {
		this.channels = channels;
	}

	public String getMerchantNo() {
		return merchantNo;
	}

	public void setMerchantNo(String merchantNo) {
		this.merchantNo = merchantNo;
	}

	public String getMerchantKey() {
		return merchantKey;
	}

	public void setMerchantKey(String merchantKey) {
		this.merchantKey = merchantKey;
	}

	public String getPayServerUrl() {
		return payServerUrl;
	}

	public void setPayServerUrl(String payServerUrl) {
		this.payServerUrl = payServerUrl;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public Configuration(Context context) {

		try {
			loadFromResource(context);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void loadFromResource(Context context) throws IOException, JSONException {

		BufferedReader reader = null;

		try {

			InputStream in = context.getApplicationContext().getResources().openRawResource(R.raw.configuration);

			reader = new BufferedReader(new InputStreamReader(in));

			Configuration configuraton = new Gson().fromJson(reader, Configuration.class);

			if(configuraton != null){
				this.copy(configuraton);
			}

		} finally {
			if (reader != null)
				reader.close();
		}
	}

	void copy(Configuration conf){

		this.merchantNo = conf.getMerchantNo();
		this.merchantKey = conf.getMerchantKey();
		this.host = conf.getHost();
		this.payServerUrl = conf.getPayServerUrl();
		this.language = conf.getLanguage();
		this.country = conf.getCountry();
		this.channels = conf.getChannels();
	}

	public SdkPayRequest createSdkRequest(){

		SdkPayRequest req = new SdkPayRequest();

		req.setMerchantNo(merchantNo);
		req.setMerchantKey(merchantKey);
		req.setPayServerUrl(host + payServerUrl);

		req.setFrpCodes(channels);

		req.setReturnUrl(host + "/pay-web-shop/return_url.jsp");
		req.setNotifyUrl(host + "/pay-web-shop/notify_url.jsp");

		req.setLanguage(language);
		req.setCountry(country);

//		autoSetChannel(req);
		return  req;
	}

	private static Map<String, String> idMaps = new HashMap<>();
	static{
		idMaps.put("ID-IB-SIM", "SIM");

		idMaps.put("ID-DCB-TELKOMSEL", "TELKOMSEL");
		idMaps.put("ID-DCB-INDOSAT", "INDOSAT");
		idMaps.put("ID-DCB-XL", "XL");
		idMaps.put("ID-DCB-H3I", "H3I");

		idMaps.put("ID-BANK-BCA", "BCA");
		idMaps.put("ID-BANK-BRI", "BRI");
		idMaps.put("ID-BANK-BII", "BII");
		idMaps.put("ID-BANK-BNI", "BNI");
		idMaps.put("ID-BANK-CIMB", "CIMB");
		idMaps.put("ID-BANK-MANDIRI", "MANDIRI");
		idMaps.put("ID-BANK-PERMATA", "PERMATA");
		idMaps.put("ID-BANK-OTHER", "OTHER");

		idMaps.put("ID-OTC-ALFAMART", "ALFAMART");
	}

	private static Map<String, String> thMaps = new HashMap<>();
	static{
		thMaps.put("TH-IB-SIM", "SIM");

		thMaps.put("TH-DCB-DTAC", "DTAC");

		thMaps.put("TH-BANK-BAY", "BAY");
		thMaps.put("TH-BANK-BBL", "BBL");
		thMaps.put("TH-BANK-CIMB", "CIMB");
		thMaps.put("TH-BANK-KBANK", "KBANK");
		thMaps.put("TH-BANK-KTB", "KTB");
		thMaps.put("TH-BANK-SCB", "SCB");
		thMaps.put("TH-BANK-TBANK", "TBANK");
		thMaps.put("TH-BANK-TMB", "TMB");

		thMaps.put("TH-OTC-BIGC", "BIGC");
		thMaps.put("TH-OTC-FAMILYMART", "FAMILYMART");
		thMaps.put("TH-OTC-MPAY", "MPAY");
		thMaps.put("TH-OTC-PAYATPOST", "PAYATPOST");
		thMaps.put("TH-OTC-TESCO", "TESCO");
		thMaps.put("TH-OTC-TOT", "TOT");
		thMaps.put("TH-OTC-TRUEMONEY", "TRUEMONEY");
		thMaps.put("TH-OTC-CENPAY", "CENPAY");

		thMaps.put("TH-VR-TRUEMONEY", "TRUEMONEY");
		thMaps.put("TH-VR-DTACCASH", "DTACCASH");
	}

	private static Map<String, String> phMaps = new HashMap<>();
	static {
		phMaps.put("PH-IB-SIM", "SIM");

		phMaps.put("PH-DCB-SMART", "SMART");

		phMaps.put("PH-BANK-METROBANK", "METROBANK");
		phMaps.put("PH-BANK-BPI", "BPI");
		phMaps.put("PH-BANK-PNB", "PNB");
		phMaps.put("PH-BANK-BDO", "BDO");
		phMaps.put("PH-BANK-RCBC", "RCBC");
		phMaps.put("PH-BANK-CHINABANK", "CHINABANK");
		phMaps.put("PH-BANK-UCPB", "UCPB");
		phMaps.put("PH-BANK-SECURITYBANK", "SECURITYBANK");
		phMaps.put("PH-BANK-UNIONBANK", "UNIONBANK");
		phMaps.put("PH-BANK-EASTWEST", "EASTWEST");
		phMaps.put("PH-BANK-MAYBANK", "MAYBANK");
		phMaps.put("PH-BANK-STERLINGBANK", "STERLINGBANK");
		phMaps.put("PH-BANK-LANDBANK", "LANDBANK");

		phMaps.put("PH-OTC-ROBINSONSSTORE", "ROBINSONSSTORE");
		phMaps.put("PH-OTC-BAYADCENTER", "BAYADCENTER");
		phMaps.put("PH-OTC-7ELEVEN", "7ELEVEN");
		phMaps.put("PH-OTC-GCASH", "GCASH");
	}

	private static Map<String, String> vnMaps = new HashMap<>();
	static {
		vnMaps.put("VN-IB-SIM", "SIM");

		vnMaps.put("VN-DCB-VIETTEL", "VIETTEL");
		vnMaps.put("VN-DCB-MOBITEL", "MOBITEL");
		vnMaps.put("VN-DCB-VINAPHONE", "VINAPHONE");

		vnMaps.put("VN-VR-1PAY", "1PAY");
	}

	private static Map<String, String> mmMaps = new HashMap<>();
	static {
		mmMaps.put("MM-IB-SIM", "SIM");
		mmMaps.put("MM-IB-CB", "CB");

		mmMaps.put("MM-DCB-OOREDOO", "OOREDOO");
		mmMaps.put("MM-DCB-TELENOR", "TELENOR");

//		mmMaps.put("MM-EW-2C2P", "2C2P");
//		mmMaps.put("MM-EW-OKDOLLAR", "OKDOLLAR");
	}

	private static Map<String, String> sgMaps = new HashMap<>();
	static {
		sgMaps.put("SG-IB-SIM", "SIM");

		sgMaps.put("SG-DCB-M1", "M1");
	}

	private static Map<String, String> myMaps = new HashMap<>();
	static {
		myMaps.put("MY-IB-SIM", "SIM");

		myMaps.put("MY-DCB-MAXIS", "MAXIS");
	}

	private static Map<String, Map<String, String>>channelMaps = new HashMap<>();

	private static Iterator<String> keys;
	static{
		channelMaps.put("ID", idMaps);
		channelMaps.put("TH", thMaps);
		channelMaps.put("PH", phMaps);
		channelMaps.put("VN", vnMaps);
		channelMaps.put("MM", mmMaps);
		channelMaps.put("SG", sgMaps);
		channelMaps.put("MY", myMaps);

		keys = channelMaps.keySet().iterator();
	}

	private void autoSetChannel(SdkPayRequest req){

		if(!keys.hasNext()){
			keys = channelMaps.keySet().iterator();
		}

		String key = keys.next();

		Object[] ks = channelMaps.get(key).keySet().toArray();

		String[] result = Arrays.copyOf(ks, ks.length, String[].class );

		req.setCountry(key);
		req.setFrpCodes(result);
	}

}
