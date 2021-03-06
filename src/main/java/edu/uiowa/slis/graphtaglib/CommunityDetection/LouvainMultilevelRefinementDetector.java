package edu.uiowa.slis.graphtaglib.CommunityDetection;

import java.util.Random;

public class LouvainMultilevelRefinementDetector extends Detector {

	public LouvainMultilevelRefinementDetector() {
		super();
	}
	
	public LouvainMultilevelRefinementDetector(double resolution) {
		super(resolution);
	}
	
    boolean detectCommunity(Network network, double resolution, Random random) {
	boolean update, update2;
	Network reducedNetwork;

	if ((network.getClusters() == null) || (network.getNNodes() == 1))
	    return false;

	update = network.runLocalMovingAlgorithm(resolution, random);

	if (network.getNClusters() < network.getNNodes()) {
	    reducedNetwork = network.getReducedNetwork();
	    reducedNetwork.initSingletonClusters();

	    update2 = detectCommunity(reducedNetwork, resolution, random);

	    if (update2) {
		update = true;

		network.mergeClusters(reducedNetwork.getClusters());

		network.runLocalMovingAlgorithm(resolution, random);
	    }
	}

	network.deleteClusteringStats();

	return update;
    }

}
