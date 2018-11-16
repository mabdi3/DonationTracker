package com.example.abdim.donationtracker;

import android.util.Log;

import com.example.abdim.donationtracker.models.Item;
import com.example.abdim.donationtracker.models.ItemCategory;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.when;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(JUnit4.class)
@PrepareForTest({ FirebaseDatabase.class})
public class BrandonUnitTest {

    @Mock
    private DatabaseReference mockedDatabaseReference;

    private Item newItem;
    private int itemId = 42069;
    private String itemName = "new item name yeet";
    private String itemDescription = "new item description yeet";
    private int itemQuantity = 200;
    private String itemLocationId = "new item location id yeet";
    private String itemLocationName = "new item location name yeet";
    private String itemTime = "new item time yeet";
    private double itemValue = 2001.09;

    @Before
    public void setup() {
        newItem = new Item(itemLocationId, itemName, itemDescription, itemQuantity, itemLocationId, null, itemTime, itemValue);

        mockedDatabaseReference = Mockito.mock(DatabaseReference.class);



//        FirebaseDatabase mockedFirebaseDatabase = Mockito.mock(FirebaseDatabase.class);
//        when(mockedFirebaseDatabase.getReference()).thenReturn(mockedDatabaseReference);
//
//        PowerMockito.mockStatic(FirebaseDatabase.class);
//        when(FirebaseDatabase.getInstance()).thenReturn(mockedFirebaseDatabase);
    }

    @Test
    public void testtest() {
        when(mockedDatabaseReference.child(anyString())).thenReturn(mockedDatabaseReference);
        assertFalse(itemInDatabase(newItem));
        addNewItem(newItem);
        assertTrue(itemInDatabase(newItem));

//        when(mockedDatabaseReference.child(anyString())).thenReturn(mockedDatabaseReference);
//        doAnswer(new Answer<Void>() {
//            @Override
//            public Void answer(InvocationOnMock invocation) throws Throwable {
//                ValueEventListener valueEventListener = (ValueEventListener) invocation.getArguments()[0];
//
//                DataSnapshot mockedDataSnapshot = Mockito.mock(DataSnapshot.class);
//                //when(mockedDataSnapshot.getValue(User.class)).thenReturn(testOrMockedUser)
//
//                valueEventListener.onDataChange(mockedDataSnapshot);
//                //valueEventListener.onCancelled(...);
//
//                return null;
//            }
//        }).when(mockedDatabaseReference).addListenerForSingleValueEvent(any(ValueEventListener.class));




//        when(mockedDatabaseReference.child(anyString())).thenReturn(mockedDatabaseReference);



        // check preferences are updated
//        DatabaseReference itemsRef = FirebaseDatabase.getInstance().getReference("items");
//        itemRef = FirebaseDatabase.getInstance().getReference("items/" + itemKey);



    }

    private boolean itemInDatabase(Item item) {
//        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
//        Query query = rootRef.child("Users").orderByChild("userName").equalTo("Nick123");
//        query.addValueEventListener(/* ... */);

        final List<Item> queriedItems = new ArrayList<>();
        Query query = mockedDatabaseReference.child("items").orderByChild("id").equalTo(item.getId());
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Item qItem = dataSnapshot.getValue(Item.class);
//                itemAdapter.add(qItem);
//                itemArray.add(qItem);
                queriedItems.add(qItem);
            }
            @Override
            public void onCancelled(DatabaseError error) {
            }
        });

        return queriedItems.contains(item);

    }

    private void addNewItem(Item item) {
        Map<String, Object> itemValues = newItem.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/items/" + newItem.getId(), itemValues);
        mockedDatabaseReference.updateChildren(childUpdates);
    }


}
