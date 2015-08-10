package com.hxy.startElse;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;

import java.util.List;

public class MyActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
       // doStartApplicationWithPackageName("com.hxy.AnimationTest1");
        Intent intent = new Intent();
       // intent.addCategory(Intent.CATEGORY_LAUNCHER);
        ComponentName cn = new ComponentName("com.hxy.AnimationTest1", "com.hxy.AnimationTest1.FadeInActivity");
        intent.setComponent(cn);
        startActivity(intent);
    }
    private void doStartApplicationWithPackageName(String packagename) {

        // ͨ��������ȡ��APP��ϸ��Ϣ������Activities��services��versioncode��name�ȵ�
        PackageInfo packageinfo = null;
        try {
            packageinfo = getPackageManager().getPackageInfo(packagename, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        if (packageinfo == null) {
            return;
        }

        // ����һ�����ΪCATEGORY_LAUNCHER�ĸð�����Intent
        Intent resolveIntent = new Intent(Intent.ACTION_MAIN, null);
        resolveIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        resolveIntent.setPackage(packageinfo.packageName);

        // ͨ��getPackageManager()��queryIntentActivities��������
        List<ResolveInfo> resolveinfoList = getPackageManager()
                .queryIntentActivities(resolveIntent, 0);

        ResolveInfo resolveinfo = resolveinfoList.iterator().next();
        if (resolveinfo != null) {
            // packagename = ����packname
            String packageName = resolveinfo.activityInfo.packageName;
            // �����������Ҫ�ҵĸ�APP��LAUNCHER��Activity[��֯��ʽ��packagename.mainActivityname]
            String className = resolveinfo.activityInfo.name;
            // LAUNCHER Intent
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            System.out.println("packageName:"+packageName+",className:"+className);
            // ����ComponentName����1:packagename����2:MainActivity·��
            ComponentName cn = new ComponentName(packageName, className);

            intent.setComponent(cn);
            startActivity(intent);
        }
    }
}
