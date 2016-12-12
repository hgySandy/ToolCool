package com.example.ljy.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.util.Xml;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.ljy.toolcool2.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class UpdateUtils extends FragmentActivity{
	private static boolean isUpdate =false;
	private static NotificationManager manager;
	private static Notification notification;
	private static int NOTIFICATION_ID ;

	/**
	 * 1.开始更新：先判断是否已经在更新，如果在更新则不重复下载
	 * @param context：下载提示显示界面
	 * @param savePath：保存路径
	 * @param updateUrl
	 */
	public static void update(Context context, String savePath,String updateUrl)
	{

		if(!isUpdate){
			Log.e("UpdateUtils", "开始下载");
			Log.e("updateUrl", updateUrl);
			Toast.makeText(context, "开始下载", Toast.LENGTH_SHORT).show();
			new UpdateAsynTask(context, savePath).execute(updateUrl);

		}
		else {
			Toast.makeText(context, "正在下载", Toast.LENGTH_SHORT).show();
			Log.e("UpdateUtils", "不要重复点击下载");

		}
	}




	/**
	 * 2.显示通知栏:
	 * @param context：用于获得通知栏管理器
	 * @param notificationId 传入notificationId
	 */
	private static void showNotification(Context context, int notificationId)
	{
		manager = (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
		// 獲得通知栏的的界面
		RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.notification_update);

		// 获得建造者
		NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
		notification = builder.setSmallIcon(R.drawable.ic_launcher)// 设置上升通知栏图片
				.setTicker("开始更新")// 设置上升通知栏显示
				.setContentTitle("下载进度")// 通知内容标题
				.setOngoing(true)// 设置可否删除
				.setAutoCancel(true)//设置点击消失
				.setContent(views).build();

		manager.notify(notificationId, notification);
	}



	/*******3.获取新版本信息（解析xml信息）**********/

	/**
	 * @param xmlmsg
	 * @param labelkey
	 * @return 获取失败返回空，成功返回对应的字符串
	 */
	public static String getNewVersioninfo(String xmlmsg,String labelkey)
	{
//		Log.e("xmlmsg", xmlmsg);
		String info=null;
		XmlPullParser parser = Xml.newPullParser();// 得到Pull解析器
		try
		{
			parser.setInput(new StringReader(xmlmsg));// 解析下载下来的字符串
			int event = parser.getEventType();// 得到第一个事件类型
			// 判断事件类型是不是文档结束 不是结束的话一直执行
			while (event != XmlPullParser.END_DOCUMENT)
			{
				switch (event)
				{
					case XmlPullParser.START_DOCUMENT:// 如果是文档开始则创建一个versinInfo对象
						break;
					case XmlPullParser.START_TAG:// 如果是标签开始则，先获取标签名再获取标签内容
						String tagName = parser.getName();// 获取标签名
//					Log.e("tagName", tagName);
						if(tagName.equals(labelkey)){
							info = parser.nextText();// 获取当前标签名内容
							return info;
						}
					default:
						break;
				}
				event = parser.next();
			}

		} catch (XmlPullParserException e)
		{
			Log.e("UpdateUtils异常", "XmlPullParserException");

			e.printStackTrace();
		} catch (IOException e)
		{
			Log.e("UpdateUtils异常", "IO异常");
			e.printStackTrace();
		}
		return info;
	}

	static class UpdateAsynTask extends AsyncTask<String, Integer, String>
	{
		private int sum;
		String savePath;
		Context context;


		public UpdateAsynTask() {
			super();
		}

		/******构造方法********/
		public UpdateAsynTask(Context context, String savePath)
		{

			super();
			isUpdate=true;
			this.savePath = savePath;
			this.context = context;
		}

		/*******下载预处理********/
		@Override
		protected void onPreExecute()
		{
			showNotification(context, NOTIFICATION_ID);
			super.onPreExecute();
		}


		/******后台运行任务(新线程)
		 *params：下载地址
		 */
		@Override
		protected String doInBackground(String... params)
		{
			Log.e("UpdateUtils", ""+params[0]);
			Log.e("UpdateUtils", "doinbackground Start");
			String result = "false";
			FileOutputStream fos = null;
			URL downURL;
			try
			{
				//获取文件信息
				downURL = new URL(params[0]);
				URLConnection Connection = downURL.openConnection();// 打开链接
				InputStream is = Connection.getInputStream();// 开流
				int fileLength = Connection.getContentLength();// 获取文件长度
//				Log.e("UpdateUtils", "fileLength:"+fileLength);
				// 判断文件是否存在 创建
				File downFile = new File(savePath);
				Log.e("UpdateUtils", "downFile:"+downFile);
				Log.e("UpdateUtils", "1 "+downFile.exists());
				if (!downFile.exists())
				{
					downFile.mkdirs();
				}
				Log.e("UpdateUtils", "2 "+downFile.exists());
				// 开流，下载
				fos = new FileOutputStream(savePath+"/update.apk");
				Log.e("UpdateUtils", fos.toString());;
				int len = 0;
				int node = 200 * 1024;
				int k = 1;
				byte[] b = new byte[1024];
				while (-1 != (len = is.read(b)))
				{
					fos.write(b, 0, len);
					sum += len;
					int i = sum * 100 / fileLength;
					if (sum > node * k)
					{
						k++;
						publishProgress(i);// 数据更新
					}
					if (sum == fileLength)
					{

						publishProgress(i);// 数据更新
					}

				}
				fos.flush();
				result = "sucess";
			} catch (MalformedURLException e1)
			{
				Log.e("UpdateException", "MalformedURLException");
				e1.printStackTrace();
			} catch (IOException e)
			{
				Log.e("UpdateException", "IOException");
				e.printStackTrace();
			} finally
			{
				if (fos != null)
				{
					try
					{
						fos.close();
					} catch (IOException e)
					{
						e.printStackTrace();
					}

				}
			}

			return result;
		}
		/***进度更新***/
		@Override
		protected void onProgressUpdate(Integer... values)
		{
			notification.contentView.setProgressBar(R.id.pb_notify_progress, 100, values[0], false);
			notification.contentView.setTextViewText(R.id.tv_notify_progress, "当前下载进度："+values[0]+"%");
			manager.notify(NOTIFICATION_ID, notification);
			super.onProgressUpdate(values);
		}

		/*******下载结束后运行*********/
		@Override
		protected void onPostExecute(String result)
		{
			switch (result)
			{
				case "false":
					Toast.makeText(context, "新版本下载失败", Toast.LENGTH_SHORT).show();

					break;
				case "sucess":
					Toast.makeText(context, "新版本下载完成", Toast.LENGTH_SHORT).show();

					initAPK();
					break;

				default:
					break;
			}
			isUpdate=false;
			super.onPostExecute(result);
		}
		private void initAPK()
		{
			// 下载完成后更显显示内容
			notification.contentView.setTextViewText(R.id.tv_notify_progress, "点击安装");
			// 创建意图对象，指定安装的文件与扩展类型
			Intent intent = new Intent(Intent.ACTION_VIEW);// 系统安装界面(跳转程序？)
			File file = new File(savePath + "/update.apk");
			intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
			notification.contentIntent = PendingIntent.getActivity(context, 0, intent, 0);
			manager.notify(NOTIFICATION_ID, notification);
		}
	}




}
