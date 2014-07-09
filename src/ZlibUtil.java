

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * zlib压缩解压工具类
 * 
 * @author Administrator
 * 
 */
public abstract class ZlibUtil {
	private final static Log log = LogFactory.getLog(ZlibUtil.class);
	//定义超过这个字节进行ZIP压缩
	public final static byte ZIP_MIN_BYTE=11;
	/**
	 * 压缩
	 * 
	 * @param data
	 *            待压缩数据
	 * @return byte[] 压缩后的数据
	 */
	public static byte[] compress(byte[] data) {
		byte[] output = new byte[0];

		Deflater compresser = new Deflater();

		compresser.reset();
		compresser.setInput(data);
		compresser.finish();
		ByteArrayOutputStream bos = new ByteArrayOutputStream(data.length);
		try {
			byte[] buf = new byte[1024];
			while (!compresser.finished()) {
				int i = compresser.deflate(buf);
				bos.write(buf, 0, i);
			}
			output = bos.toByteArray();
		} catch (Exception e) {
			output = data;
			log.error("", e);
		} finally {
			try {
				bos.close();
			} catch (IOException e) {
				log.error("", e);
			}
		}
		compresser.end();
		return output;
	}

	/**
	 * 压缩
	 * 
	 * @param data
	 *            待压缩数据
	 * 
	 * @param os
	 *            输出流
	 */
	public static void compress(byte[] data, OutputStream os) {
		DeflaterOutputStream dos = new DeflaterOutputStream(os);
		try {
			dos.write(data, 0, data.length);
			dos.finish();
			dos.flush();
		} catch (IOException e) {
			log.error("", e);
		}
	}

	/**
	 * 解压缩
	 * 
	 * @param data
	 *            待压缩的数据
	 * @return byte[] 解压缩后的数据
	 */
	public static byte[] decompress(byte[] data) {
		byte[] output = new byte[0];
		Inflater decompresser = new Inflater();
		decompresser.reset();
		decompresser.setInput(data);
		ByteArrayOutputStream o = new ByteArrayOutputStream(data.length);
		try {
			byte[] buf = new byte[1024];
			while (!decompresser.finished()) {
				int i = decompresser.inflate(buf);
				o.write(buf, 0, i);
			}
			output = o.toByteArray();
		} catch (Exception e) {
			output = data;
			log.error("", e);
		} finally {
			try {
				o.close();
			} catch (IOException e) {
				log.error("", e);
			}
		}

		decompresser.end();
		return output;
	}

	/**
	 * 解压缩
	 * 
	 * @param is
	 *            输入流
	 * @return byte[] 解压缩后的数据
	 */
	public static byte[] decompress(InputStream is) {
		InflaterInputStream iis = new InflaterInputStream(is);
		ByteArrayOutputStream o = new ByteArrayOutputStream(1024);
		try {
			int i = 1024;
			byte[] buf = new byte[i];
			while ((i = iis.read(buf, 0, i)) > 0) {
				o.write(buf, 0, i);
			}
		} catch (IOException e) {
			log.error("", e);
		}
		return o.toByteArray();
	}
	
	
	/**
	 * 字符串压缩
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static String compress(String str){
		if (str == null || str.length() == 0) {
			return str;
		}
		GZIPOutputStream gzip = null;
		ByteArrayOutputStream out =null;
		try{
			out = new ByteArrayOutputStream();
			gzip = new GZIPOutputStream(out);
			gzip.write(str.getBytes(CharsetConfig.UTF_8));
			gzip.close();
			out.close();
			return out.toString(CharsetConfig.ISO_8859_1);
		} catch (IOException e) {
			log.error("", e);
		}finally{
			if(gzip!=null){
				try {
					gzip.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

	/**
	 * 字符串解压缩
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static String uncompress(String str) throws IOException {
		if (str == null || str.length() == 0) {
			return str;
		}
		ByteArrayOutputStream out =null;
		ByteArrayInputStream  in  =null;
		GZIPInputStream gunzip =   null;
		try{
			out = new ByteArrayOutputStream();
			in = new ByteArrayInputStream(str.getBytes(CharsetConfig.ISO_8859_1));
			gunzip = new GZIPInputStream(in);
			byte[] buffer = new byte[256];
			int n;
			while ((n = gunzip.read(buffer)) >= 0) {
				out.write(buffer, 0, n);
			}
			gunzip.close();
			in.close();
			out.close();
			return out.toString(CharsetConfig.UTF_8);
		}catch (IOException e) {
			log.error("", e);
		}finally{
			if(gunzip!=null){
				try {
					gunzip.close();
				} catch (IOException e) {
					log.error("", e);
				}
			}
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					log.error("", e);
				}
			}
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					log.error("", e);
				}
			}
		}
		return null;
	}
	
	
	public static String uncompressUtf8(String str){
		if (str == null || str.length() == 0) {
			return str;
		}
		try{
			int idx = str.indexOf('_');
			String len = str.substring(0,idx);
			String cont = str.substring(idx+1,str.length());
			byte[] compressArr = new byte[Integer.parseInt(len)];
			int i = 0;
			for(int j=0;j<cont.length();j++){
				char f0 = cont.charAt(j);
				if(f0 == 'z'){
					compressArr[i++] = (byte)(-(cont.charAt(++j)+30));
				}else if(f0 =='{'){
					compressArr[i++] = (byte)(cont.charAt(++j)+30);
				}else if(f0 =='\n'){
					compressArr[i++] = (byte)(cont.charAt(++j)-32);
				}else if(f0 =='}'){
					compressArr[i++] = (byte)(-(cont.charAt(++j)-32));
				}else if(f0 =='~'){
					compressArr[i++] = (byte)(-(cont.charAt(++j)-22));
				}else{
					compressArr[i++] = (byte)(f0-22);
				}
			}
			byte[] decompressArr =ZlibUtil.decompress(compressArr);
			return new String(decompressArr,CharsetConfig.UTF_8);
		} catch (UnsupportedEncodingException e) {
			log.error("",e);
		}
		return null;
	}
	public static String compressUtf8(String str){
		if (str == null || str.length() == 0) {
			return str;
		}
		try {
			byte[] utf8Arr = str.getBytes(CharsetConfig.UTF_8);
			byte[] compressArr =ZlibUtil.compress(utf8Arr);
			//压缩力度如果小于1/3的话，小面的逻辑就没必要走了 基本上都是超过原字节大小，直接转明较为划算.
			if((compressArr.length*1.5)>utf8Arr.length){
				return null;
			}
			StringBuilder sb = new StringBuilder();
			sb.append(compressArr.length).append("_");
			for(byte compress:compressArr){
				if(compress > 99){  // 100 ~ 128   98~70
					sb.append("{").append((char)(compress-30));//123
				}else if(compress>9){ // 10 ~ 99  32 ~ 121
					sb.append((char)(compress+22));  
				}else if(compress>-1){ // 0 ~ 9  32 ~ 41
					sb.append("\n").append((char)(compress+32)); // 10
				}else if(compress>-10){ // -9 ~ -1 41 ~ 33
					sb.append("}").append((char)(Math.abs(compress)+32)); // 125
				}else if(compress>-100){ // -99 ~ -10  121~32
					sb.append("~").append((char)(Math.abs(compress)+22)); // 126
				}else{ // -127 ~ -100  97~70
					sb.append("z").append((char)(Math.abs(compress)-30)); // 122
				}
			}
			if(utf8Arr.length >= sb.length()){
				return sb.toString();
			}
		} catch (UnsupportedEncodingException e) {
			log.error("",e);
		}
		return null;
	}
	
	public static void main(String args[]) throws UnsupportedEncodingException {
		String str = "测试的数据看是否需要压缩了数据，学同喜同喜小花洒或多或少谁谁谁测试的数据看是否需要压缩了数据，学同喜同喜小花洒或多或少谁谁谁测试的数据看是否需要压缩了数据，学同喜同喜小花洒或多或少谁谁谁测试的数据看是否需要压缩了数据，学同喜同喜小花洒或多或少谁谁谁 ";
		//压缩
		String compress = compressUtf8(str);
		if(compress!=null){
			//解压
			String uncompress = uncompressUtf8(compress);
			System.out.println("压缩前字节大小:"+str.getBytes(CharsetConfig.UTF_8).length);
			System.out.println("压缩后字节大小:"+compress.getBytes(CharsetConfig.UTF_8).length);
			System.out.println("值是否相等 :"+uncompress.equals(str));
		}
		
	}
}
