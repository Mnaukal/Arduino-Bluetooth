package com.thetopfer.arduino_bluetooth.Fragments;

import android.app.Fragment;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.thetopfer.arduino_bluetooth.MainActivity;
import com.thetopfer.arduino_bluetooth.R;

import java.util.ArrayList;

public class TerminalFragment extends Fragment implements BluetoothFragment {

    MainActivity mainActivity;
    ImageButton Send;
    ListView messagesList;
    EditText messageText;
    ArrayList<Message> list;
    MessageAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_terminal,container,false);
        mainActivity = ((MainActivity)getActivity());

        //call the widgets
        Send = (ImageButton) rootView.findViewById(R.id.sendButton);
        messagesList = (ListView) rootView.findViewById(R.id.messagesList);
        messageText = (EditText) rootView.findViewById(R.id.messageText) ;

        list = new ArrayList();

        adapter = new MessageAdapter(mainActivity,android.R.layout.simple_list_item_1, list);
        messagesList.setAdapter(adapter);
        messagesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = ((TextView) view).getText().toString();
                messageText.setText(text);
            }
        });

        //commands to be sent to bluetooth
        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageText.getText().toString();
                list.add(new Message(message, true));
                messagesList.setSelection(adapter.getCount() - 1);
                mainActivity.SendBluetoothMessage(message);
                messageText.setText("");
            }
        });

        return rootView;
    }

    public void ReceiveBluetoothMessage(String message)
    {
        list.add(new Message(message, false));
        adapter.notifyDataSetChanged();
        messagesList.setSelection(adapter.getCount() - 1);
    }

    public class Message {
        public String message;
        public Boolean fromThisDevice;

        public Message(String Message, Boolean FromThisDevice) {
            message = Message;
            fromThisDevice = FromThisDevice;
        }

        @Override
        public String toString()
        {
            return message;
        }
    }

    public class MessageAdapter extends ArrayAdapter<Message> {
        int Layout;

        public MessageAdapter(Context context, int layout, ArrayList<Message> items) {
            super(context, layout, items);
            Layout = layout;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Message msg = getItem(position);

            View view = super.getView(position, convertView, parent);

            if(msg.fromThisDevice)
            {
                ((TextView) view).setTextColor(getResources().getColor(R.color.colorAccent));
            }
            else
            {
                ((TextView) view).setTextColor(getResources().getColor(R.color.colorPrimary));
            }

            return view;
        }
    }
}
