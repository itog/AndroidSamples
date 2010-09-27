package com.itog_lab.android.sample;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Main extends Activity {
	private static final String TAG = "Sample";
	private Button playButton;
	private Button failExampleButton;
	private SoundPool soundPool;
	private Context context;

	private int[] soundResouces = {
			R.raw.bashi2,
			R.raw.bogo,
			R.raw.bokaan,
			R.raw.dogu,
			R.raw.fin,
			R.raw.kaze,
			R.raw.muchi,
			R.raw.pachi,
			R.raw.pikopiko,
			R.raw.zasi
	};
	private int[] soundIds;
	private int index;
	private boolean test;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		context = this.getApplicationContext();

		soundPool = new SoundPool(10, AudioManager.STREAM_MUSIC, 0);

//		test = true;
		if (test) {
			// load()の限界に挑戦
			// ANRくらうときもある
			soundIds = new int[260];
			for (int i=0; i<260; i++) {
				soundIds[i] = soundPool.load(context, R.raw.bashi2, 1);
			}
		} else {
			// 通常の利用方法
			soundIds = new int[soundResouces.length];
			for (int i=0; i<soundResouces.length; i++) {
				soundIds[i] = soundPool.load(context, soundResouces[i], 1);
			}
		}

		playButton = (Button)findViewById(R.id.PlayButton);
		playButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (index >= soundIds.length) {
					index = 0;
				}
				Log.v(TAG, "index = " + index);
				soundPool.play(soundIds[index++], 100, 100, 1, 0, 1);
			}        	
		});

		failExampleButton = (Button)findViewById(R.id.FailExampleButton);
		failExampleButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				int soundId = soundPool.load(context, R.raw.bashi2, 1);
				soundPool.play(soundId, 100, 100, 1, 0, 1);
			}
		});
	}
}