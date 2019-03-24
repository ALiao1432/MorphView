package study.ian.morphview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import study.ian.morphviewlib.MorphView;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private MorphView morphView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
        initViewPath();

//        morphView.setOnClickListener(v -> {
//            if (morphView.isRunningInfiniteAnim()) {
//                morphView.stopInfiniteAnimation();
//                morphView.performAnimation(R.drawable.vd_0);
//            } else {
////                morphView.performInfiniteAnimation(
////                        R.drawable.vd_search_1,
////                        R.drawable.vd_search_2,
////                        R.drawable.vd_search_3,
////                        R.drawable.vd_search_4,
////                        R.drawable.vd_search_5
////                );
//                morphView.performInfiniteAnimation(
//                        R.drawable.vd_0,
//                        R.drawable.vd_1,
//                        R.drawable.vd_2,
//                        R.drawable.vd_3,
//                        R.drawable.vd_4,
//                        R.drawable.vd_5,
//                        R.drawable.vd_6,
//                        R.drawable.vd_7,
//                        R.drawable.vd_8,
//                        R.drawable.vd_9
//                );
//            }
//        });
    }

    private void findViews() {
        morphView = findViewById(R.id.morphView);
    }

    private void initViewPath() {
        morphView.setCurrentId(R.drawable.vd_0);
        morphView.setSize(540, 540);
        morphView.setPaintColor(0xff000000);
        morphView.setPaintWidth(50);
    }
}
