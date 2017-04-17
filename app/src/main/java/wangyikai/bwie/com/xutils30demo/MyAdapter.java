package wangyikai.bwie.com.xutils30demo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.image.ImageOptions;
import org.xutils.x;

/**
 * date: 2017/4/16.
 * author: 王艺凯 (lenovo )
 * function:
 */

public class MyAdapter extends BaseAdapter {
    private MyBean mBean;
    private Context mContext;

    public MyAdapter(MyBean bean, Context context) {
        mBean = bean;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mBean.getData().getComics().size();
    }

    @Override
    public Object getItem(int position) {
        return mBean.getData().getComics().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.lv_n, null);
            vh = new ViewHolder();
            vh.tv = (TextView) convertView.findViewById(R.id.tv);
            vh.iv = (ImageView) convertView.findViewById(R.id.iv);

            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.tv.setText(mBean.getData().getComics().get(position).getTitle());
        ImageOptions options = new ImageOptions.Builder()
                //设置加载过程中的图片
                .setLoadingDrawableId(R.mipmap.ic_launcher)
                //设置加载失败后的图片
                .setFailureDrawableId(R.mipmap.ic_launcher)
                //设置使用缓存
                .setUseMemCache(true)
                //设置显示圆形图片
                .setCircular(true)
                //设置支持gif
                .setIgnoreGif(false)

                .setCrop(true)
                .setAutoRotate(true)
                .setCircular(true)
                .setFadeIn(true)
                .setSquare(true)
                .setForceLoadingDrawable(true)
                .build();

        x.image().bind(vh.iv, mBean.getData().getComics().get(position).getCover_image_url(), options);

        return convertView;
    }

    class ViewHolder {
        ImageView iv;
        TextView tv;
    }
}
