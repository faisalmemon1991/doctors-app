package com.app.doctorsapp.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.app.doctorsapp.R;
import com.app.doctorsapp.models.DiagnosisModel;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.realm.OrderedRealmCollection;
import io.realm.RealmBaseAdapter;

/**
 * Created by faisal on 9/10/16.
 */
public class DiagnosisAdapter extends RealmBaseAdapter<DiagnosisModel> implements ListAdapter {

//This adapter is added to MainActivity Listview

    class Holder {
        @Bind(R.id.diagnosis_result)
        TextView diagnosisResult;

        public Holder(View view) {
            ButterKnife.bind(this, view);
        }
    }


    public DiagnosisAdapter(Context context, OrderedRealmCollection<DiagnosisModel> data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Holder viewHolder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_diagnosis_main, parent, false);
            viewHolder = new Holder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (Holder) convertView.getTag();
        }

        DiagnosisModel diagnosisModel = adapterData.get(position);
        viewHolder.diagnosisResult.setText(diagnosisModel.getResult());

        return convertView;
    }
}
