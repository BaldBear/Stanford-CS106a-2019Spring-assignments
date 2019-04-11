/* 
 * Note: these methods are public in order for them to be used by other files
 * in this assignment; DO NOT change them to private.  You may add additional
 * private methods to implement required functionality if you would like.
 * 
 * You should remove the stub lines from each method and replace them with your
 * implementation that returns an updated image.
 */

// TODO: comment this file explaining its behavior

import acm.graphics.*;

public class DarkRoomAlgorithms implements DarkRoomAlgorithmsInterface {

	public GImage rotateLeft(GImage source) {
		int[][] pixels = source.getPixelArray();
		int[][] newPixels = new int[pixels[0].length][pixels.length];
		for(int i = 0; i <pixels.length; i++) {
			for(int j = 0; j < pixels[0].length; j++) {
				newPixels[pixels[0].length-1-j][i] = pixels[i][j];
			}
		}
		return new GImage(newPixels);
	}

	public GImage rotateRight(GImage source) {
		int[][] pixels = source.getPixelArray();
		int[][] newPixels = new int[pixels[0].length][pixels.length];
		for(int i = 0; i <pixels.length; i++) {
			for(int j = 0; j < pixels[0].length; j++) {
				newPixels[j][pixels.length - 1 - i] = pixels[i][j];
			}
		}
		return new GImage(newPixels);
	}

	public GImage flipHorizontal(GImage source) {
		int[][] pixels = source.getPixelArray();
		int[][] newPixels = new int[pixels.length][pixels[0].length];
		for(int i = 0; i <pixels.length; i++) {
			for(int j = 0; j < pixels[0].length; j++) {
				newPixels[i][pixels[0].length - 1 - j] = pixels[i][j];
			}
		}
		return new GImage(newPixels);
	}

	public GImage negative(GImage source) {
		int[][] pixels = source.getPixelArray();
		int[][] newPixels = new int[pixels.length][pixels[0].length];
		for(int i = 0; i <pixels.length; i++) {
			for(int j = 0; j < pixels[0].length; j++) {
				int newRed = 255 - GImage.getRed(pixels[i][j]);
				int newGreen = 255 - GImage.getGreen(pixels[i][j]);
				int newBlue = 255 - GImage.getBlue(pixels[i][j]);
				newPixels[i][j] = GImage.createRGBPixel(newRed, newGreen, newBlue);
			}
		}
		return new GImage(newPixels);
	}

	public GImage greenScreen(GImage source) {
		int[][] pixels = source.getPixelArray();
		int[][] newPixels = new int[pixels.length][pixels[0].length];
		for(int i = 0; i <pixels.length; i++) {
			for(int j = 0; j < pixels[0].length; j++) {
				int red = GImage.getRed(pixels[i][j]);
				int green = GImage.getGreen(pixels[i][j]);
				int blue = GImage.getBlue(pixels[i][j]);
				if(green >= 2 * Math.max(red, blue)) {
					newPixels[i][j] = GImage.createRGBPixel(red, green, blue, 0);
				}
				else {
					newPixels[i][j] = GImage.createRGBPixel(red, green, blue);
				}	
			}
		}
		return new GImage(newPixels);
	}

	public GImage blur(GImage source) {
		int[][] pixels = source.getPixelArray();
		int[][] newPixels = new int[pixels.length][pixels[0].length];
		for(int i = 0; i <pixels.length; i++) {
			for(int j = 0; j < pixels[0].length; j++) {
				if(i == 0) {
					if(j == 0) {
						int[] red = new int[4];
						int[] green = new int[4];
						int[] blue = new int[4];
						red[0] = GImage.getRed(pixels[0][0]);
						green[0] = GImage.getGreen(pixels[0][0]);
						blue[0] = GImage.getBlue(pixels[0][0]);
						red[1] = GImage.getRed(pixels[1][0]);
						green[1] = GImage.getGreen(pixels[1][0]);
						blue[1] = GImage.getBlue(pixels[1][0]);
						red[2] = GImage.getRed(pixels[0][1]);
						green[2] = GImage.getGreen(pixels[0][1]);
						blue[2] = GImage.getBlue(pixels[0][1]);
						red[3] = GImage.getRed(pixels[1][1]);
						green[3] = GImage.getGreen(pixels[1][1]);
						blue[3] = GImage.getBlue(pixels[1][1]);
						red[0] = (int)(red[0] +red[1] + red[2] + red[3])/4;
						green[0] = (int)(green[0] +green[1] + green[2] + green[3])/4;
						blue[0] = (int)(blue[0] +blue[1] + blue[2] + blue[3])/4;
						newPixels[0][0] = GImage.createRGBPixel(red[0], green[0], blue[0]);
					}
					else if(j == pixels[0].length - 1) {
						int[] red = new int[4];
						int[] green = new int[4];
						int[] blue = new int[4];
						red[0] = GImage.getRed(pixels[0][pixels[0].length-1]);
						green[0] = GImage.getGreen(pixels[0][pixels[0].length-1]);
						blue[0] = GImage.getBlue(pixels[0][pixels[0].length-1]);
						red[1] = GImage.getRed(pixels[1][pixels[0].length-1]);
						green[1] = GImage.getGreen(pixels[1][pixels[0].length-1]);
						blue[1] = GImage.getBlue(pixels[1][pixels[0].length-1]);
						red[2] = GImage.getRed(pixels[0][pixels[0].length-2]);
						green[2] = GImage.getGreen(pixels[0][pixels[0].length-2]);
						blue[2] = GImage.getBlue(pixels[0][pixels[0].length-2]);
						red[3] = GImage.getRed(pixels[1][pixels[0].length-2]);
						green[3] = GImage.getGreen(pixels[1][pixels[0].length-2]);
						blue[3] = GImage.getBlue(pixels[1][pixels[0].length-2]);
						red[0] = (int)(red[0] +red[1] + red[2] + red[3])/4;
						green[0] = (int)(green[0] +green[1] + green[2] + green[3])/4;
						blue[0] = (int)(blue[0] +blue[1] + blue[2] + blue[3])/4;
						newPixels[0][pixels[0].length-1] = GImage.createRGBPixel(red[0], green[0], blue[0]);
					}
					else {
						int[] red = new int[6];
						int[] green = new int[6];
						int[] blue = new int[6];
						red[0] = GImage.getRed(pixels[0][j-1]);
						green[0] = GImage.getGreen(pixels[0][j-1]);
						blue[0] = GImage.getBlue(pixels[0][j-1]);
						red[1] = GImage.getRed(pixels[0][j]);
						green[1] = GImage.getGreen(pixels[0][j]);
						blue[1] = GImage.getBlue(pixels[0][j]);
						red[2] = GImage.getRed(pixels[0][j+1]);
						green[2] = GImage.getGreen(pixels[0][j+1]);
						blue[2] = GImage.getBlue(pixels[0][j+1]);
						red[3] = GImage.getRed(pixels[1][j-1]);
						green[3] = GImage.getGreen(pixels[1][j-1]);
						blue[3] = GImage.getBlue(pixels[1][j-1]);
						red[4] = GImage.getRed(pixels[1][j]);
						green[4] = GImage.getGreen(pixels[1][j]);
						blue[4] = GImage.getBlue(pixels[1][j]);
						red[5] = GImage.getRed(pixels[0][j+1]);
						green[5] = GImage.getGreen(pixels[0][j+1]);
						blue[5] = GImage.getBlue(pixels[0][j+1]);
						red[0] = (int)(red[0]+red[1]+red[2]+red[3]+red[4]+red[5])/6;
						green[0] = (int)(green[0]+green[1]+green[2]+green[3]+green[4]+green[5])/6;
						blue[0] = (int)(blue[0]+blue[1]+blue[3]+blue[4]+blue[5])/6;
						newPixels[0][j] = GImage.createRGBPixel(red[0], green[0], blue[0]);
					}

				}
				else if( i == pixels.length-1) {
					if(j ==0) {
						int[] red = new int[4];
						int[] green = new int[4];
						int[] blue = new int[4];
						red[0] = GImage.getRed(pixels[pixels.length-1][0]);
						green[0] = GImage.getGreen(pixels[pixels.length-1][0]);
						blue[0] = GImage.getBlue(pixels[pixels.length-1][0]);
						red[1] = GImage.getRed(pixels[pixels.length-2][0]);
						green[1] = GImage.getGreen(pixels[pixels.length-2][0]);
						blue[1] = GImage.getBlue(pixels[pixels.length-2][0]);
						red[2] = GImage.getRed(pixels[pixels.length-1][1]);
						green[2] = GImage.getGreen(pixels[pixels.length-1][1]);
						blue[2] = GImage.getBlue(pixels[pixels.length-1][1]);
						red[3] = GImage.getRed(pixels[pixels.length-2][1]);
						green[3] = GImage.getGreen(pixels[pixels.length-2][1]);
						blue[3] = GImage.getBlue(pixels[pixels.length-2][1]);
						red[0] = (int)(red[0] +red[1] + red[2] + red[3])/4;
						green[0] = (int)(green[0] +green[1] + green[2] + green[3])/4;
						blue[0] = (int)(blue[0] +blue[1] + blue[2] + blue[3])/4;
						newPixels[pixels.length-1][0] = GImage.createRGBPixel(red[0], green[0], blue[0]);
					}
					else if(j == pixels[0].length-1) {
						int[] red = new int[4];
						int[] green = new int[4];
						int[] blue = new int[4];
						red[0] = GImage.getRed(pixels[pixels.length-1][pixels[0].length-1]);
						green[0] = GImage.getGreen(pixels[pixels.length-1][pixels[0].length-1]);
						blue[0] = GImage.getBlue(pixels[pixels.length-1][pixels[0].length-1]);
						red[1] = GImage.getRed(pixels[pixels.length-2][pixels[0].length-1]);
						green[1] = GImage.getGreen(pixels[pixels.length-2][pixels[0].length-1]);
						blue[1] = GImage.getBlue(pixels[pixels.length-2][pixels[0].length-1]);
						red[2] = GImage.getRed(pixels[pixels.length-1][pixels[0].length-2]);
						green[2] = GImage.getGreen(pixels[pixels.length-1][pixels[0].length-2]);
						blue[2] = GImage.getBlue(pixels[pixels.length-1][pixels[0].length-2]);
						red[3] = GImage.getRed(pixels[pixels.length-2][pixels[0].length-2]);
						green[3] = GImage.getGreen(pixels[pixels.length-2][pixels[0].length-2]);
						blue[3] = GImage.getBlue(pixels[pixels.length-2][pixels[0].length-2]);
						red[0] = (int)(red[0] +red[1] + red[2] + red[3])/4;
						green[0] = (int)(green[0] +green[1] + green[2] + green[3])/4;
						blue[0] = (int)(blue[0] +blue[1] + blue[2] + blue[3])/4;
						newPixels[pixels.length-1][pixels[0].length-2] = GImage.createRGBPixel(red[0], green[0], blue[0]);
					}
					else {
						int[] r = new int[6];
						int[] g = new int[6];
						int[] b = new int[6];
						r[0] = GImage.getRed(pixels[pixels.length-1][j-1]);
						g[0] = GImage.getGreen(pixels[pixels.length-1][j-1]);
						b[0] = GImage.getBlue(pixels[pixels.length-1][j-1]);
						r[1] = GImage.getRed(pixels[pixels.length-1][j]);
						g[1] = GImage.getGreen(pixels[pixels.length-1][j]);
						b[1] = GImage.getBlue(pixels[pixels.length-1][j]);
						r[2] = GImage.getRed(pixels[pixels.length-1][j+1]);
						g[2] = GImage.getGreen(pixels[pixels.length-1][j+1]);
						b[2] = GImage.getBlue(pixels[pixels.length-1][j+1]);
						r[3] = GImage.getRed(pixels[pixels.length-2][j-1]);
						g[3] = GImage.getGreen(pixels[pixels.length-2][j-1]);
						b[3] = GImage.getBlue(pixels[pixels.length-2][j-1]);
						r[4] = GImage.getRed(pixels[pixels.length-2][j]);
						g[4] = GImage.getGreen(pixels[pixels.length-2][j]);
						b[4] = GImage.getBlue(pixels[pixels.length-2][j]);
						r[5] = GImage.getRed(pixels[pixels.length-2][j+1]);
						g[5] = GImage.getGreen(pixels[pixels.length-2][j+1]);
						b[5] = GImage.getBlue(pixels[pixels.length-2][j+1]);
						r[0] = (int)(r[0]+r[1]+r[2]+r[3]+r[4]+r[5]);
						g[0] = (int)(g[0]+g[1]+g[2]+g[3]+g[4]+g[5]);
						b[0] = (int)(b[0]+b[1]+b[2]+b[3]+b[4]+b[5]);
						newPixels[pixels.length-1][j] = GImage.createRGBPixel(r[0], g[0], b[0]);
						}
				}
				else if(j == 0) {
					int[] r = new int[6];
					int[] g = new int[6];
					int[] b = new int[6];
					r[0] = GImage.getRed(pixels[i-1][0]);
					g[0] = GImage.getGreen(pixels[i-1][0]);
					b[0] = GImage.getBlue(pixels[i-1][0]);
					r[1] = GImage.getRed(pixels[i][0]);
					g[1] = GImage.getGreen(pixels[i][0]);
					b[1] = GImage.getBlue(pixels[i][0]);
					r[2] = GImage.getRed(pixels[i+1][0]);
					g[2] = GImage.getGreen(pixels[i+1][0]);
					b[2] = GImage.getBlue(pixels[i+1][0]);
					r[3] = GImage.getRed(pixels[i-1][1]);
					g[3] = GImage.getGreen(pixels[i-1][1]);
					b[3] = GImage.getBlue(pixels[i-1][1]);
					r[4] = GImage.getRed(pixels[i][1]);
					g[4] = GImage.getGreen(pixels[i][1]);
					b[4] = GImage.getBlue(pixels[i][1]);
					r[5] = GImage.getRed(pixels[i+1][1]);
					g[5] = GImage.getGreen(pixels[i+1][1]);
					b[5] = GImage.getBlue(pixels[i+1][1]);
					r[0] = (int)(r[0]+r[1]+r[2]+r[3]+r[4]+r[5])/6;
					g[0] = (int)(g[0]+g[1]+g[2]+g[3]+g[4]+g[5])/6;
					b[0] = (int)(b[0]+b[1]+b[2]+b[3]+b[4]+b[5])/6;
					newPixels[i][0] = GImage.createRGBPixel(r[0], g[0], b[0]);
				}
				else if(j == pixels[0].length-1) {
					int[] r = new int[6];
					int[] g = new int[6];
					int[] b = new int[6];
					r[0] = GImage.getRed(pixels[i-1][pixels[0].length-1]);
					g[0] = GImage.getGreen(pixels[i-1][pixels[0].length-1]);
					b[0] = GImage.getBlue(pixels[i-1][pixels[0].length-1]);
					r[1] = GImage.getRed(pixels[i][pixels[0].length-1]);
					g[1] = GImage.getGreen(pixels[i][pixels[0].length-1]);
					b[1] = GImage.getBlue(pixels[i][pixels[0].length-1]);
					r[2] = GImage.getRed(pixels[i+1][pixels[0].length-1]);
					g[2] = GImage.getGreen(pixels[i+1][pixels[0].length-1]);
					b[2] = GImage.getBlue(pixels[i+1][pixels[0].length-1]);
					r[3] = GImage.getRed(pixels[i-1][pixels[0].length-2]);
					g[3] = GImage.getGreen(pixels[i-1][pixels[0].length-2]);
					b[3] = GImage.getBlue(pixels[i-1][pixels[0].length-2]);
					r[4] = GImage.getRed(pixels[i][pixels[0].length-2]);
					g[4] = GImage.getGreen(pixels[i][pixels[0].length-2]);
					b[4] = GImage.getBlue(pixels[i][pixels[0].length-2]);
					r[5] = GImage.getRed(pixels[i+1][pixels[0].length-2]);
					g[5] = GImage.getGreen(pixels[i+1][pixels[0].length-2]);
					b[5] = GImage.getBlue(pixels[i+1][pixels[0].length-2]);
					r[0] = (int)(r[0]+r[1]+r[2]+r[3]+r[4]+r[5])/6;
					g[0] = (int)(g[0]+g[1]+g[2]+g[3]+g[4]+g[5])/6;
					b[0] = (int)(b[0]+b[1]+b[2]+b[3]+b[4]+b[5])/6;
					newPixels[i][pixels[0].length-2] = GImage.createRGBPixel(r[0], g[0], b[0]);
				}
				else {
					int[] r = new int[9];
					int[] g = new int[9];
					int[] b = new int[9];
					r[0] = GImage.getRed(pixels[i-1][j-1]);
					g[0] = GImage.getGreen(pixels[i-1][j-1]);
					b[0] = GImage.getBlue(pixels[i-1][j-1]);
					r[1] = GImage.getRed(pixels[i-1][j]);
					g[1] = GImage.getGreen(pixels[i-1][j]);
					b[1] = GImage.getBlue(pixels[i-1][j]);
					r[2] = GImage.getRed(pixels[i-1][j+1]);
					g[2] = GImage.getGreen(pixels[i-1][j+1]);
					b[2] = GImage.getBlue(pixels[i-1][j+1]);
					r[3] = GImage.getRed(pixels[i][j-1]);
					g[3] = GImage.getGreen(pixels[i][j-1]);
					b[3] = GImage.getBlue(pixels[i][j-1]);
					r[4] = GImage.getRed(pixels[i][j]);
					g[4] = GImage.getGreen(pixels[i][j]);
					b[4] = GImage.getBlue(pixels[i][j]);
					r[5] = GImage.getRed(pixels[i][j+1]);
					g[5] = GImage.getGreen(pixels[i][j+1]);
					b[5] = GImage.getBlue(pixels[i][j+1]);
					r[6] = GImage.getRed(pixels[i+1][j-1]);
					g[6] = GImage.getGreen(pixels[i+1][j-1]);
					b[6] = GImage.getBlue(pixels[i+1][j-1]);
					r[7] = GImage.getRed(pixels[i+1][j]);
					g[7] = GImage.getGreen(pixels[i+1][j]);
					b[7] = GImage.getBlue(pixels[i+1][j]);
					r[8] = GImage.getRed(pixels[i+1][j+1]);
					g[8] = GImage.getGreen(pixels[i+1][j+1]);
					b[8] = GImage.getBlue(pixels[i+1][j+1]);
					r[0] = (int)(r[0]+r[1]+r[2]+r[3]+r[4]+r[5]+r[6]+r[7]+r[8])/9;
					g[0] = (int)(g[0]+g[1]+g[2]+g[3]+g[4]+g[5]+g[6]+g[7]+g[8])/9;
					b[0] = (int)(b[0]+b[1]+b[2]+b[3]+b[4]+b[5]+b[6]+b[7]+b[8])/9;
					newPixels[i][j] = GImage.createRGBPixel(r[0], g[0], b[0]);
				}
			}
		}
		return new GImage(newPixels);
	}

	public GImage crop(GImage source, int cropX, int cropY, int cropWidth, int cropHeight) {
		int[][] pixels = source.getPixelArray();
		int[][] newPixels = new int[cropWidth][cropHeight];
		for(int i =0; i < cropWidth; i++) {
			for(int j = 0; j < cropHeight; j++) {
				newPixels[i][j] = pixels[cropX+i][cropY+j];
			}
		}		
		return new GImage(newPixels);
	}

	public GImage equalize(GImage source) {
		int[][] pixels = source.getPixelArray();
		int[][] newPixels = new int[pixels.length][pixels[0].length];
		int[][] luminosity = new int[pixels.length][pixels[0].length];
		int[] cumulativeL = new int[256];
		int[] luminosityH = new int[256];
		for(int i = 0; i < pixels.length; i++) {
			for(int j = 0; j < pixels[0].length; j++) {
				luminosity[i][j] = computeLuminosity(GImage.getRed(pixels[i][j]), GImage.getGreen(pixels[i][j]), GImage.getBlue(pixels[i][j]));
				for(int k = 0; k < 256; k++) {
					if(luminosity[i][j] == k) {
						luminosityH[k] += 1;
					}
				}
			}
		}
		cumulativeL[0] += luminosityH[0];
		for(int i = 1; i < 256; i++) {
			cumulativeL[i] =cumulativeL[i-1] + luminosityH[i];
		}
		//handout 里的公式把R, G, B都设成统一值，会令输出图像变成黑白
		for(int i = 0; i < pixels.length; i++) {
			for(int j = 0; j < pixels[0].length; j++) {
				int newR = GImage.getRed(pixels[i][j]) * cumulativeL[luminosity[i][j]] / (pixels.length * pixels[0].length);
				int newG = GImage.getGreen(pixels[i][j]) * cumulativeL[luminosity[i][j]] / (pixels.length * pixels[0].length);
				int newB = GImage.getBlue(pixels[i][j]) * cumulativeL[luminosity[i][j]] / (pixels.length * pixels[0].length);
				newPixels[i][j] = GImage.createRGBPixel(newR, newG, newB);
			}
		}
		
		return new GImage(newPixels);
	}
}
