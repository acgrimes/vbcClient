package com.dd.vbc.network;

import com.dd.vbc.enums.Response;
import com.dd.vbc.mvc.model.BallotBean;
import com.dd.vbc.mvc.model.Serialization;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.dd.vbc.utils.SerialUtil.byteArrayToInt;

public class ElectionResponse extends Serialization implements Serializable {

    private Response response;
    private List<BallotBean> ballotBeanList;

    public ElectionResponse() {}
    public ElectionResponse(Response response, List<BallotBean> ballotBeanList) {
        this.response = response;
        this.ballotBeanList = ballotBeanList;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public List<BallotBean> getBallotBeanList() {
        return ballotBeanList;
    }

    public void setBallotBeanList(List<BallotBean> ballotBeanList) {
        this.ballotBeanList = ballotBeanList;
    }

    public byte[] serialize0() {

        byte[] bytes = null;
        switch(response) {
            case Authentication: {
                    bytes = response.serialize();
                    break;
            }
            case Ballot: {
                List<byte[]> ballotBeanListBytes = ballotBeanList.stream().
                        map(ballotBean -> {
                            byte[] ballotBeanBytes = ballotBean.serialize();
                            byte[] ballotBeanByteLength = serializeInt(ballotBeanBytes.length);
                            byte[] byteLengthConcat = concatenateBytes(ballotBeanByteLength, ballotBeanBytes);
                            return byteLengthConcat;
                        }).collect(Collectors.toList());
                bytes = concatenateBytes(ballotBeanListBytes.toArray(new byte[ballotBeanListBytes.size()][]));
                break;
            }
        }
        return bytes;
    }

    public void deserialize0(byte[] bytes) {
        int index=0;
        response = Response.fromOrdinal(deserializeInt(Arrays.copyOfRange(bytes, 0, index=index+4)));
        switch(response) {
            case Authentication: {

                break;
            }
            case Ballot: {
                List<String> ballotBeanList = new ArrayList<>();
                while(index<bytes.length) {
                    int ballotBeanLength = deserializeInt(Arrays.copyOfRange(bytes, index, index=index+4));
                    String ballotBean = deserializeString(Arrays.copyOfRange(bytes, index, index=index+ballotBeanLength));
                    ballotBeanList.add(ballotBean);
                }
                break;
            }
        }
    }

    public static byte[] serialize(ElectionResponse electionResponse) {

        byte[] result = null;
        try(final ByteArrayOutputStream bos = new ByteArrayOutputStream();
            final ObjectOutput out = new ObjectOutputStream(bos);) {
                out.writeObject(electionResponse);
                result = bos.toByteArray();
        } catch(IOException ioe) {
            ioe.printStackTrace();
        }
        return result;
    }

    public static ElectionResponse deserialize(byte[] objectStream) {

        ElectionResponse electionResponse = null;
        try(ByteArrayInputStream bis = new ByteArrayInputStream(objectStream);
            ObjectInput in = new ObjectInputStream(bis)) {
            electionResponse = (ElectionResponse) in.readObject();;
        } catch(IOException | ClassNotFoundException ioe) {
            ioe.printStackTrace();
        }
        return electionResponse;
    }

    private static Response setResponse(byte[] message) {

        byte[] responseTypeLength = new byte[4];
        for(int i=0; i<4; i++) {
            responseTypeLength[i] = message[i];
        }
        int ord = byteArrayToInt(responseTypeLength);
        return Response.fromOrdinal(ord);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ElectionResponse that = (ElectionResponse) o;

        return new EqualsBuilder()
                .append(response, that.response)
                .append(ballotBeanList, that.ballotBeanList)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(response)
                .append(ballotBeanList)
                .toHashCode();
    }
}
