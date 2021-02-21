package com.srpl.crm.web.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestServlet
 */
@WebServlet("/image/*")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	private static final int DEFAULT_BUFFER_SIZE = 10240; // 10KB.

	// Properties
	// ---------------------------------------------------------------------------------

	// Actions
	// ------------------------------------------------------------------------------------

	public void init() throws ServletException {

		// Define base path somehow. You can define it as init-param of the
		// servlet.
		// ServletContext ctx = (ServletContext)
		// FacesContext.getCurrentInstance()
		// .getExternalContext().getContext();

		// In a Windows environment with the Applicationserver running on the
		// c: volume, the above path is exactly the same as "c:\images".
		// In UNIX, it is just straightforward "/images".
		// If you have stored files in the WebContent of a WAR, for example in
		// the
		// "/WEB-INF/images" folder, then you can retrieve the absolute path by:
		// this.imagePath = getServletContext().getRealPath("/WEB-INF/images");
	}

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	public void test() {
		System.out.println("test");
	}

	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String path = "";
		String module = request.getParameter("mod");
		String imagePath = getServletContext().getRealPath("");
		
		imagePath = imagePath.substring(0, imagePath.indexOf("standalone"));
		
		String pathSeparator = File.separator;
		if (pathSeparator.equals("\\")) {
			pathSeparator = "\\\\";
		} 
		imagePath += "standalone"+pathSeparator+"deployments";
		switch (module) {
		case "upp": //user profile pic
			path = request.getSession().getServletContext()
					.getInitParameter("userProfilePicPath");
			break;
		case "companyLogo": //company logo
			path = "/images/companyLogo/crm/";
			break;
		}

		path = path.replace("/", pathSeparator);
		imagePath += path;
		// System.out.println("Path in servlet is "+this.imagePath);
		// Get requested image by path info.
		String requestedImage = request.getPathInfo();
		// System.out.println("Requested image is "+requestedImage);
		// Check if file name is actually supplied to the request URI.
		if (requestedImage == null) {
			// Do your thing if the image is not supplied to the request URI.
			// Throw an exception, or send 404, or show default/warning image,
			// or just ignore it.
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			return;
		}

		// Decode the file name (might contain spaces and on) and prepare file
		// object.
		File image = new File(imagePath, URLDecoder.decode(requestedImage,
				"UTF-8"));

		// Check if file actually exists in filesystem.
		if (!image.exists()) {
			// Do your thing if the file appears to be non-existing.
			// Throw an exception, or send 404, or show default/warning image,
			// or just ignore it.
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			return;
		}

		// Get content type by filename.
		String contentType = getServletContext().getMimeType(image.getName());

		// Check if file is actually an image (avoid download of other files by
		// hackers!).
		// For all content types, see:
		// http://www.w3schools.com/media/media_mimeref.asp
		if (contentType == null || !contentType.startsWith("image")) {
			// Do your thing if the file appears not being a real image.
			// Throw an exception, or send 404, or show default/warning image,
			// or just ignore it.
			response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
			return;
		}

		// Init servlet response.
		response.reset();
		response.setBufferSize(DEFAULT_BUFFER_SIZE);
		response.setContentType(contentType);
		response.setHeader("Content-Length", String.valueOf(image.length()));
		response.setHeader("Content-Disposition",
				"inline; filename=\"" + image.getName() + "\"");

		// Prepare streams.
		BufferedInputStream input = null;
		BufferedOutputStream output = null;

		try {
			// Open streams.
			input = new BufferedInputStream(new FileInputStream(image),
					DEFAULT_BUFFER_SIZE);
			output = new BufferedOutputStream(response.getOutputStream(),
					DEFAULT_BUFFER_SIZE);

			// Write file contents to response.
			byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
			int length;
			while ((length = input.read(buffer)) > 0) {
				output.write(buffer, 0, length);
			}
		} finally {
			// Gently close streams.
			close(output);
			close(input);
		}

	}

	private static void close(Closeable resource) {
		if (resource != null) {
			try {
				resource.close();
			} catch (IOException e) {
				// Do your thing with the exception. Print it, log it or mail
				// it.
				e.printStackTrace();
			}
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("Do get called ---------");
	}

}
