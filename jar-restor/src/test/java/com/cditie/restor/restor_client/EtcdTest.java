package com.cditie.restor.restor_client;

import mousio.etcd4j.EtcdClient;
import mousio.etcd4j.responses.EtcdKeysResponse;

import java.net.URI;

/**
 * Created by zhuyunhui on 9/11/2017.
 */
public class EtcdTest {
	public static void main(String[] args) throws  Exception{
		EtcdClient etcd = new EtcdClient(URI.create("http://192.168.33.87:2379"));
		// Logs etcd version
		System.out.println(etcd.getVersion());
		EtcdKeysResponse response = etcd.get("/sgp/http/info/nginx-ui/sat-06/pod_ip").send().get();
		System.out.println(response.getNode().getValue());
		etcd.close();

	}
}
